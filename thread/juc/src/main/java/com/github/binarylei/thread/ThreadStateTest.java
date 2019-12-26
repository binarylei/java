package com.github.binarylei.thread;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author leigang
 * @version 2019-05-11
 * @since 2.0.0
 */
public class ThreadStateTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadStateTest test = new ThreadStateTest();
        // 1. WAITING
        new Thread(test::testWaitingBySleep, "testWaitingBySleep").start();
        new Thread(test::testWaitingByPark, "testWaitingByPark").start();
        // 2. TIME_WAITING
        new Thread(test::testWaitingByWait, "testWaitingByWait").start();
        new Thread(test::testTimedWaiting, "testTimedWaiting").start();

        // 3. BLOCKED
        new Thread(test::sleepForever, "sleepForever").start();
        Thread.sleep(200);
        new Thread(test::testBlockBySynchronized, "testBlockBySynchronized").start();
    }

    @Test
    public synchronized void testWaitingByWait() {
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void testWaitingByPark() {
        LockSupport.park();
    }

    @Test
    public synchronized void testTimedWaiting() {
        try {
            wait(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void testWaitingBySleep() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public synchronized void sleepForever() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public synchronized void testBlockBySynchronized() {
        System.out.println("testBlockBySynchronized");
    }
}
