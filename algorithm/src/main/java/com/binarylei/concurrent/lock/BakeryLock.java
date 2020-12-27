package com.binarylei.concurrent.lock;

/**
 * BakeryLock锁算法：采用分布式版本来保证先来先服务特性：每个线程都有一个序号，一直等待，直到没有序号比自己更早的线程深度进行临界区为止。
 * <p>
 * 任意一种无死锁的lock算法在最坏情况下至少需要读写n个不同的存储单元
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class BakeryLock implements Lock {

    private final int n = 5;
    private volatile boolean[] flag;
    private volatile int[] label;

    public BakeryLock(int n) {
        flag = new boolean[n];
        label = new int[n];
        for (int i = 0; i < n; i++) {
            flag[i] = false;
            label[i] = 0;
        }
    }

    // while ((∃k != id) (level[k] >= i) 表示当k!=id时，存在level[k]>=i则自旋
    @Override
    public void lock() {
        int id = (int) Thread.currentThread().getId() % n;
        flag[id] = true;
//        label[id] = Math.max(label[0], label[1],... , label[n-1]) + 1;
        // spin while conficts exist
//       while ((∃k != id) (flag[k] && (label[k], k) << (label[id], id))) {}
//       }
    }

    @Override
    public void unlock() {
        int id = (int) Thread.currentThread().getId() % 2;
        flag[id] = false;
    }
}
