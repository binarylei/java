package com.github.binarylei._1_1thread;

public class MyThread extends Thread {

    private int count = 5;

    //synchronized加锁
    public synchronized void run () {
        System.out.printf("线程%s执行，count：%s\n", this.currentThread().getName(),--count);
    }

    public static void main(String[] args) {
        /**
         * 当多个线程访问MyThread的run方法时，以排队的方式进行处理(这里的排序是按照CPU分配的先后顺序而定的)
         * 一个线程要执行synchronized修饰的方法时
         *  1. 尝试获得锁，如果拿到锁则执行该方法
         *  2. 这个线程就会不断尝试获得这把锁，直到拿到为止，而且是多个线程同时去竞争这把锁。(也就是会有锁竞争的问题)
         */
        MyThread thread = new MyThread();
        new Thread(thread, "t1").start();
        new Thread(thread, "t2").start();
        new Thread(thread, "t3").start();
        new Thread(thread, "t4").start();
        new Thread(thread, "t5").start();
    }
}
