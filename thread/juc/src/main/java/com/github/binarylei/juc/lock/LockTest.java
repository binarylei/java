package com.github.binarylei.juc.lock;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author leigang
 * @version 2019-05-07
 * @since 2.0.0
 */
public class LockTest {

    @Test
    public void test2() {

    }

    @Test
    public void test() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.lock();
        lock.unlock();
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
//        for (int i = 0; i < 10; i++) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                sleep(new Random().nextInt(2000) + 1000L);
                lock.unlock();
            }
        }, "sub-1").start();
//        }
//        sleep(Long.MAX_VALUE);
    }

    public static void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
