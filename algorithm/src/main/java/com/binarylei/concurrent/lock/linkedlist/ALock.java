package com.binarylei.concurrent.lock.linkedlist;

import com.binarylei.concurrent.lock.Lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ALOCK：对 Backofflockl 的改进，因为它将无效性降到最低，并把一个线程释放锁和另一个线程获得该锁之间的时间间隔最小化。与 TASLOCKI和 Backofflock 不同，该算法能够确保无饥饿性，同时也保证了先来先服务的公平性。
 * Alock 锁并不是空间有效的。它要求并发线程的最大个数为一个已知的界限 n，同时为每个锁分配一个与该界限大小相同的数组。因此，即使一个线程每次只访问一个锁，同步 L 个不同对象也需要 O(Ln) 大小的空间。
 *
 * <pre>
 *             false   false               true       false
 *     flag数组： 0  ->   1  ->   ...  ->   slot  ->   slot+1
 * </pre>
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class ALock implements Lock {
    private ThreadLocal<Integer> mySlotIndex = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    AtomicInteger tail;
    boolean[] flag;
    int size;

    public ALock(int capacity) {
        this.size = capacity;
        tail = new AtomicInteger(0);
        flag = new boolean[capacity];
        flag[0] = true;
    }

    @Override
    public void lock() {
        int slot = tail.getAndIncrement() % size;
        mySlotIndex.set(slot);
        while (!flag[slot]) {
        }
    }

    @Override
    public void unlock() {
        int slot = mySlotIndex.get();
        flag[slot] = false;
        flag[(slot + 1) % size] = true;
    }
}
