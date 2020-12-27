package com.binarylei.concurrent.lock;

/**
 * 过滤锁算法：Peterson锁算法在多线程上的一般化。
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class FilterLock implements Lock {

    private final int n = 5;
    private volatile int[] level = new int[n];
    private volatile int[] victim = new int[n];

    // while ((∃k != id) (level[k] >= i) 表示当k!=id时，存在level[k]>=i则自旋
    @Override
    public void lock() {
        int id = (int) Thread.currentThread().getId() % n;
        for (int i = 1; i < n; i++) {
            level[id] = i;
            victim[i] = id;
            // spin while conficts exist
//            while ((∃k != id) (level[k] >= i && victim[i] == id)) {}
//            }

        }
    }

    @Override
    public void unlock() {
        int id = (int) Thread.currentThread().getId() % 2;
        level[id] = 0;
    }
}
