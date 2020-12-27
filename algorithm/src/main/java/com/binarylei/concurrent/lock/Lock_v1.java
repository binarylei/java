package com.binarylei.concurrent.lock;

/**
 * 双线程锁算法：线程的标识为 0-1，i 表示当着线程，1-i则表示另一个线程
 * 缺点：如果两个线程交叉执行，都执行到完 flag[i]=true，则会发生死锁。但两个线程先后执行则不会发生死锁。
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class Lock_v1 implements Lock {

    private boolean[] flag = new boolean[2];

    @Override
    public void lock() {
        int i = (int) Thread.currentThread().getId() % 2;
        flag[i] = true;        // I'm interested
        while (flag[1 - i]) {  // wait, other thread is running
        }
    }

    @Override
    public void unlock() {
        int i = (int) Thread.currentThread().getId() % 2;
        flag[i] = false;
    }
}
