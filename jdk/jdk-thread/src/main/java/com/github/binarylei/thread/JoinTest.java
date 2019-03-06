package com.github.binarylei.thread;

/**
 * @author: leigang
 * @version: 2018-05-06
 */
public class JoinTest extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("子线程：" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JoinTest t = new JoinTest();
        t.start();
        t.join();

        System.out.println("main 线程：" + Thread.currentThread().getName());
    }
}
