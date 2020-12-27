package com.binarylei.concurrent.juc.lock;

import org.junit.Test;

import java.util.concurrent.locks.StampedLock;

/**
 * @author binarylei
 * @version 2020-03-21
 */
public class StampedLockTest {

    @Test
    public void testStampedLock() {

        final StampedLock sl = new StampedLock();

        // 获取/释放悲观读锁示意代码
        long stamp = sl.readLock();
        try {
            //省略业务相关代码
        } finally {
            sl.unlockRead(stamp);
        }

        // 获取/释放写锁示意代码
        /*long stamp = sl.writeLock();
        try {
            //省略业务相关代码
        } finally {
            sl.unlockWrite(stamp);
        }*/
    }
}
