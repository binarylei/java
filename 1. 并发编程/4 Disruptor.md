# Disruptor

> [Disruptor](http://ifeve.com/disruptor/)：是一个开源的并发框架，能够在无锁的情况下实现网络的Queue并发操作。

## Disruptor特点

* Disruptor 是一个 Java 的并发编程框架，大大的简化了并发程序开发的难度，在性能上也比 Java 本身提供的一些并发包要好。

* Disruptor 是一个高性能异步处理框架，它实现了观察者模式。

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
