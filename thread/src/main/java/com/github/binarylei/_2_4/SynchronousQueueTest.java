package com.github.binarylei._2_4;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {

    public static void main(String[] args) {
        //1. LinkedBlockingQueue: 无界阻塞队列
        SynchronousQueue q = new SynchronousQueue();
        //q.add("a"); //java.lang.IllegalStateException: Queue full

        final SynchronousQueue queue = new SynchronousQueue();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object o = queue.take();
                    System.out.println(o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                queue.add("aaa");
            }
        });

        t2.start();
        t1.start();

    }
}
