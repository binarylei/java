package com.github.binarylei.thread.volatile1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: leigang
 * @version: 2018-05-06
 */
public class VolatileNotAtomic extends Thread {

    private static volatile int count;
    private static AtomicInteger count2 = new AtomicInteger(0);

    public static void addCount() {
        for (int i = 0; i < 1000; i++) {
            count++;
            count2.incrementAndGet();
        }
        System.out.println(count);
        System.out.println(count2);
        //使用volatile关键字，最后的结果不是1000*10
        //要想保证原子性，可以使用AtomicInteger类，只保证最后的结果正确，中间可能有误
    }

    public void run () {
        addCount();
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileNotAtomic[] vna = new VolatileNotAtomic[10];
        for (int i = 0; i < 10; i++) {
            vna[i] = new VolatileNotAtomic();
        }
        for (int i = 0; i < vna.length  ; i++) {
            vna[i].start();
        }
    }
}
