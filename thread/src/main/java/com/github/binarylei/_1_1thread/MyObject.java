package com.github.binarylei._1_1thread;

public class MyObject {

    public synchronized void method1 () {
        try {
            System.out.printf("%s\n", Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public /*synchronized*/ void method2 () {
        System.out.printf("%s\n", Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        final MyObject m = new MyObject();
        new Thread(new Runnable() {
            public void run() {
                m.method1();
            }
        }, "t1").start();
        new Thread(new Runnable() {
            public void run() {
                m.method2();
            }
        }, "t2").start();
    }
}
