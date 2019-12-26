package com.github.binarylei.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author leigang
 * @version 2019-05-09
 * @since 2.0.0
 */
public class CyclicBarrierTest {


    public static void main(String[] args) {
        new CyclicBarrierTest().test();
    }

    public ReentrantLock lock = new ReentrantLock();

    public void test() {
        for (int i = 0; i < 2; i++) {
            final int n = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final ReentrantLock lock = CyclicBarrierTest.this.lock;
                    System.out.println(Thread.currentThread().getName() + " before get lock");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " after get lock");
                    if (n == 0) {
                        try {
                            Thread.sleep(20000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " after unlock");
                }
            }).start();
        }
    }

}
