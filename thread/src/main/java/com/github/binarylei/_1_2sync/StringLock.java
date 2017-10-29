package com.github.binarylei._1_2sync;

public class StringLock {

    public void method () {
        //字符串常量：只有一个引用，只有一个线程进入
        //synchronized ("字符串常量") {
        synchronized (new String("字符串常量")) {
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + ": start");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ":end");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        /**
         * synchronized (new String("字符串常量"))：只有一个线程进入
         * t1: start
         * t1:end
         * t1: start
         * t1:end
         * t1: start
         * t1:end
         *
         * synchronized ("字符串常量")：两个线程进入
         * t1: start
         * t2: start
         * t1:end
         * t1: start
         * t2:end
         * t2: start
         * t2:end
         */
        final StringLock obj = new StringLock();
        new Thread(new Runnable() {
            public void run() {
                obj.method();
            }
        }, "t1").start();

        new Thread(new Runnable() {
            public void run() {
                obj.method();
            }
        }, "t2").start();
    }
}
