package com.github.binarylei.thread;

import java.util.Random;

/**
 * 多个线程挣抢同一份资源，容易造成死锁
 * @author: leigang
 * @version: 2018-05-05
 */
public class DeadLock2 {

    public static void main(String[] args) {
        Object goods = new Object();
        Object money = new Object();

        new Test1(goods, money).start();
        new Test2(goods, money).start();

    }

    static class Test1 extends Thread {
        private Object goods;
        private Object money;

        public Test1(Object goods, Object money) {
            this.goods = goods;
            this.money = money;
        }

        @Override
        public void run() {
            while (true) {
                test();
            }
        }

        public void test() {
            synchronized (goods) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (money) {
                }
            }
            System.out.println(Thread.currentThread().getName() + "先交货再给钱");
        }
    }

    static class Test2 extends Thread {
        private Object goods;
        private Object money;

        public Test2(Object goods, Object money) {
            this.goods = goods;
            this.money = money;
        }

        @Override
        public void run() {
            while (true) {
                test();
            }
        }

        public void test() {
            synchronized (money) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (goods) {
                }
            }
            System.out.println(Thread.currentThread().getName() + "先给钱再交货");
        }
    }
}
