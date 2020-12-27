package com.binarylei.concurrent.lock.linkedlist;

import com.binarylei.concurrent.lock.Lock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Test-And-Set
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class TASLock implements Lock {
    private AtomicBoolean state = new AtomicBoolean(false);

    @Override
    public void lock() {
        while (state.getAndSet(true)) {   // state: false -> true
        }
    }

    @Override
    public void unlock() {
        state.set(false);
    }
}
