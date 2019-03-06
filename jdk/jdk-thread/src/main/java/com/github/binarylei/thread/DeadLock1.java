package com.github.binarylei.thread;

/**
 * @author: leigang
 * @version: 2018-05-06
 */
public class DeadLock1 extends Object {
    private String objID;

    public DeadLock1(String id) {
    objID = id;
    }

    public synchronized void checkOther(DeadLock1 other) {
        System.out.println(Thread.currentThread().getName() + ": entering checkOther()");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            ;
        }
        System.out.println(Thread.currentThread().getName() +
                ": in checkOther() - about to " + "invoke 'other.action()'");

        //调用other对象的action方法，由于该方法是同步方法，因此会试图获取other对象的对象锁
        other.action();
        System.out.println(Thread.currentThread().getName() + ": leaving checkOther()");
    }

    public synchronized void action() {
        System.out.println(Thread.currentThread().getName() + ": entering action()");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            ;
        }
        System.out.println(Thread.currentThread().getName() + ": leaving action()");
    }

    public static void main(String[] args) {
        final DeadLock1 obj1 = new DeadLock1("obj1");
        final DeadLock1 obj2 = new DeadLock1("obj2");

        Runnable runA = new Runnable() {
            public void run() {
                obj1.checkOther(obj2);
            }
        };
        Thread threadA = new Thread(runA, "threadA");
        threadA.start();

        Runnable runB = new Runnable() {
            public void run() {
                obj2.checkOther(obj1);
            }
        };
        Thread threadB = new Thread(runB, "threadB");
        threadB.start();
    }
}
