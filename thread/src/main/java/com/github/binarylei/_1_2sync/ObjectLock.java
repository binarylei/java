package com.github.binarylei._1_2sync;

public class ObjectLock {

    //对象锁
    public void method1 () {
        synchronized (this) {
            try {
                System.out.println("do method1 ...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //类级别锁
    public void method2 () {
        synchronized (this.getClass()) {
            try {
                System.out.println("do method2 ...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //私有对象锁
    private Object lock = new Object();
    public void method3 () {
        synchronized (lock) {
            try {
                System.out.println("do method3 ...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                new ObjectLock().method1();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                new ObjectLock().method2();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                new ObjectLock().method3();
            }
        }).start();
    }
}

