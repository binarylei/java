package com.github.binarylei.design.producer_consumer;

import java.util.concurrent.*;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Data> data = new LinkedBlockingQueue<Data>();

        Producter p1 = new Producter(data);
        Producter p2 = new Producter(data);
        Producter p3 = new Producter(data);

        Consumer consumer1 = new Consumer(data);
        Consumer consumer2 = new Consumer(data);
        Consumer consumer3 = new Consumer(data);

//        ExecutorService pool = Executors.newCachedThreadPool();
//        pool.execute(producter1);
//        pool.execute(producter2);
//        pool.execute(producter3);
//        pool.execute(consumer1);
//        pool.execute(consumer2);
//        pool.execute(consumer3);
        System.out.println(data.size());
        new Thread(p1).start();
        new Thread(p2).start();
        new Thread(p3).start();


        Thread.sleep(5000);
        System.out.println(data.size());
    }
}
