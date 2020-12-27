package com.binarylei.concurrent.lock;

/**
 * Peterson锁算法：综合 Lock_v1 和 Lock_v2 两种算法，构造出一种无饥饿的锁算法。是最简洁、最完美的双线程互斥算法。
 * 1. 线程 A/B 先后执行：Lock_v1 生效。flag
 * 2. 线程 A/B 先交叉执行：Lock_v2 生效。victim
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class PetersonLock implements Lock {

    private volatile boolean[] flag = new boolean[2];
    private volatile int victim;

    @Override
    public void lock() {
        int i = (int) Thread.currentThread().getId()%2;
        flag[i] = true;                   // I'm interested
        victim = i;                       // you go first
        // flag[1 - i]: 其它线程正在运行，阻塞
        // victim == i: 除非其它线程放行，否则阻塞
        while (flag[1 - i] && victim == i) {  // wait
        }
    }

    @Override
    public void unlock() {
        int i = (int) Thread.currentThread().getId() % 2;
        flag[i] = false;
    }
}
