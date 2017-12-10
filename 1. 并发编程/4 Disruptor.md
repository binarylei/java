# Disruptor

> [Disruptor](http://ifeve.com/disruptor/)：是一个开源的并发框架，能够在无锁的情况下实现网络的Queue并发操作。

## Disruptor特点

* Disruptor 是一个 Java 的并发编程框架，大大的简化了并发程序开发的难度，在性能上也比 Java 本身提供的一些并发包要好。

* Disruptor 是一个高性能异步处理框架，它实现了观察者模式，或者事件监听模式的实现。

* Disruptor 是无锁的、CPU友好，它不会清除缓存中的数据，只会覆盖，降低了垃圾回收机制启动的频率。

## 获得Disruptor

可以通过[Maven](http://mvnrepository.com/artifact/com.lmax/disruptor)安装Disruptor。查看[Getting-Started](http://ifeve.com/disruptor-getting-started/)快速入门，[更多API](http://lmax-exchange.github.io/disruptor/docs/)

```xml
<dependency>
    <groupId>com.lmax</groupId>
    <artifactId>disruptor</artifactId>
    <version>3.3.7</version>
</dependency>
```

## Disruptor之HelloWorld

我们从一个简单的例子开始学习Disruptor：生产者传递一个long类型的值给消费者，而消费者消费这个数据的方式仅仅是把它打印出来。首先声明一个Event来包含需要传递的数据：

```java
public class LongEvent { 
    private long value;
    public long getValue() { 
        return value; 
    } 
 
    public void setValue(long value) { 
        this.value = value; 
    } 
} 
```

由于需要让Disruptor为我们创建事件，我们同时还声明了一个EventFactory来实例化Event对象。

```java
public class LongEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
```

我们还需要一个事件消费者，也就是一个事件处理器。这个事件处理器简单地把事件中存储的数据打印到终端：

```java
public class LongEventHandler implements EventHandler<LongEvent> { 
    @Override 
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception { 
        System.out.println(longEvent.getValue()); 
    } 
}
```

事件都会有一个生成事件的源，可以简单的理解为一个事件生产者。这个例子中假设事件是由于磁盘IO或者network读取数据的时候触发的，事件源使用一个ByteBuffer来模拟它接受到的数据，也就是说，事件源会在IO读取到一部分数据的时候触发事件（触发事件不是自动的，程序员需要在读取到数据的时候自己触发事件并发布）：

```java
public class LongEventProducer {
	private final RingBuffer<LongEvent> ringBuffer;
	public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	/**
	 * onData用来发布事件，每调用一次就发布一次事件
	 * @param ByteBuffer 它的参数会用过事件传递给消费者
	 */
	public void onData(ByteBuffer bb){
		//1.可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
		long sequence = ringBuffer.next();
		try {
			//2.用上面的索引取出一个空的事件用于填充（获取该序号对应的事件对象）
			LongEvent event = ringBuffer.get(sequence);
			//3.获取要通过事件传递的业务数据
			event.setValue(bb.getLong(0));
		} finally {
			//4.发布事件
			//注意，最后的 ringBuffer.publish 方法必须包含在 finally 中以确保必须得到调用；如果某个请求的 sequence 未被提交，将会堵塞后续的发布操作或者其它的 producer。
			ringBuffer.publish(sequence);
		}
	}	
}
```

很明显的是：当用一个简单队列来发布事件的时候会牵涉更多的细节，这是因为事件对象还需要预先创建。发布事件最少需要两步：获取下一个事件槽并发布事件（发布事件的时候要使用try/finnally保证事件一定会被发布）。如果我们使用RingBuffer.next()获取一个事件槽，那么一定要发布对应的事件。如果不能发布事件，那么就会引起Disruptor状态的混乱。尤其是在多个事件生产者的情况下会导致事件消费者失速，从而不得不重启应用才能会恢复。

最后一步就是把所有的代码组合起来完成一个完整的事件处理系统。

```java
public class LongEventMain {

	public static void main(String[] args) throws Exception {
		//1. 创建缓冲池
		ExecutorService  executor = Executors.newCachedThreadPool();
		//2. 创建工厂
		LongEventFactory factory = new LongEventFactory();
		//3. 创建bufferSize ,也就是RingBuffer大小，必须是2的N次方
		int ringBufferSize = 1024 * 1024; // 

		//4. 创建disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, executor);

		//5. 连接消费事件方法
		disruptor.handleEventsWith(new LongEventHandler());
		
		//6. 启动
		disruptor.start();
		
		//Disruptor 的事件发布过程是一个两阶段提交的过程：
		//7. 发布事件
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		
		LongEventProducer producer = new LongEventProducer(ringBuffer); 
		//LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		for(long l = 0; l<100; l++){
			byteBuffer.putLong(0, l);
			producer.onData(byteBuffer);
			//Thread.sleep(1000);
		}

		disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
		executor.shutdown();//关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭，disruptor 在 shutdown 时不会自动关闭；		
	}
}
```

## Disruptor术语说明

* `RingBuffer`: 被看作Disruptor最主要的组件，然而从3.0开始RingBuffer仅仅负责存储和更新在Disruptor中流通的数据。对一些特殊的使用场景能够被用户(使用其他数据结构)完全替代。

* `Sequence`: Disruptor使用Sequence来表示一个特殊组件处理的序号。和Disruptor一样，每个消费者(EventProcessor)都维持着一个Sequence。大部分的并发代码依赖这些Sequence值的运转，因此Sequence支持多种当前为AtomicLong类的特性。

* `Sequencer`: 这是Disruptor真正的核心。实现了这个接口的两种生产者（单生产者和多生产者）均实现了所有的并发算法，为了在生产者和消费者之间进行准确快速的数据传递。

* `SequenceBarrier`: 由Sequencer生成，并且包含了已经发布的Sequence的引用，这些的Sequence源于Sequencer和一些独立的消费者的Sequence。它包含了决定是否有供消费者来消费的Event的逻辑。

* `WaitStrategy`：决定一个消费者将如何等待生产者将Event置入Disruptor。

* `Event`：从生产者到消费者过程中所处理的数据单元。Disruptor中没有代码表示Event，因为它完全是由用户定义的。

* `EventProcessor`：主要事件循环，处理Disruptor中的Event，并且拥有消费者的Sequence。它有一个实现类是BatchEventProcessor，包含了event loop有效的实现，并且将回调到一个EventHandler接口的实现对象。

* `EventHandler`：由用户实现并且代表了Disruptor中的一个消费者的接口。

* `Producer`：由用户实现，它调用RingBuffer来插入事件(Event)，在Disruptor中没有相应的实现代码，由用户实现。

* `WorkProcessor`：确保每个sequence只被一个processor消费，在同一个WorkPool中的处理多个WorkProcessor不会消费同样的sequence。

* `WorkerPool`：一个WorkProcessor池，其中WorkProcessor将消费Sequence，所以任务可以在实现WorkHandler接口的worker吃间移交

* `LifecycleAware`：当BatchEventProcessor启动和停止时，于实现这个接口用于接收通知。


