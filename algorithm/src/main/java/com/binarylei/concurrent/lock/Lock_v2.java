package com.binarylei.concurrent.lock;

/**
 * 双线程锁算法：同 Lock_v1 算法正好相反，如果两个线程交叉执行，不会出现死锁。但两个线程先后执行则发生死锁。
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class Lock_v2 implements Lock {

    private volatile int victim;

    @Override
    public void lock() {
        int i = (int) Thread.currentThread().getId();
        victim = i;            // let other go first
        while (victim == i) {  // wait
        }
    }

    @Override
    public void unlock() {
    }
}
