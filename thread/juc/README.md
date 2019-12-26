# java.util.concurrent

## 悲观锁与乐观锁

悲观锁与乐观锁是一种广义概念，体现的是看待线程同步的不同角度。

悲观锁认为自己在使用数据的时候一定有别的线程来修改数据，在获取数据的时候会先加锁，确保数据不会被别的线程修改。

锁实现：关键字 synchronized、接口 Lock 的实现类。

适用场景：写操作较多，先加锁可以保证写操作时数据正确

乐观锁认为自己在使用数据时不会有别的线程修改数据，所以不会添加锁，只是在更新数据的时候去判断之前有没有别的线程更新了这个数据。

锁实现：CAS 算法，例如 AtomicInteger 类的原子自增是同过CAS自实现

适用场景：读操作较多，不加锁的特点能够使其读操作的性能大幅提升

### CAS

全名: Compare And Swap(比较与交换)

无锁算法：基于使件原语实现，在不使用锁(没有线程被阻寒)的情况下实现多线程之间的变量同步。

JDK 中实现：java.util.concurrent 包中的原子类(AtomicInteger)就是通过 CAS 来实现了乐观锁

算法涉及到三个操作数：

* 需要读写的内存值V
* 进行比较的值A
* 要写入的新值B

ABA 问题

Atomicinteger getand DecrementUnsafe . getandaddlnt

<https://www.bilibili.com/video/av48693160/>
《Java AbstractQueuedSynchronizer源码阅读3-cancelAcquire()》：<https://www.jianshu.com/p/01f2046aab64>
《AbstractQueuedSynchronizer的介绍和原理分析》：<http://ifeve.com/introduce-abstractqueuedsynchronizer/>



