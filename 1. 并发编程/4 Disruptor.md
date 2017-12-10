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


