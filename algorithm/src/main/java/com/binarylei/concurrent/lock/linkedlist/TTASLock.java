package com.binarylei.concurrent.lock.linkedlist;

import com.binarylei.concurrent.lock.Lock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * TTASLock：由线程反复地读锁直到该锁看起来是空闲的(即直到 get() 返回 false)。只有在锁看似为空闲时，线程オ能使用 testAndSet()。这种技术称为测试一测试一设置。
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class TTASLock implements Lock {
    private AtomicBoolean state = new AtomicBoolean();

    @Override
    public void lock() {
        while (true) {
            while (state.get()) {                   // wait, other thread holds the lock. state=true
            }
            if (!state.getAndSet(true)) { // state: false -> true
                return;
            }
        }

    }

    @Override
    public void unlock() {
        state.set(false);
    }
}
