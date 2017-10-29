package com.github.binarylei._1_2sync;

public class SyncDemo1 {

    public synchronized void method1 () {
        System.out.println("method1");
        method2();
    }

    public synchronized void method2 () {
        System.out.println("method2");
        method3();
    }

    public synchronized void method3 () {
        System.out.println("method3");
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                new SyncDemo1().method1();
            }
        }).start();
    }
}
