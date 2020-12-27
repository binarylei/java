package com.binarylei.concurrent.lock.linkedlist;

import com.binarylei.concurrent.lock.Lock;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * BackOffLock：关键是 minDelay, maxDelay 的设置。实验表明，最优值与处理器的个数以及它们的速度密切相关。因此。很难调整 Backofflock类以使它与各种不同的机器相互兼容。
 * 争用：指多个线程试图同时获取一个锁;
 * 高争用：则意味着存在大量正在争用的线程;
 * 低争用：与高争用相反。
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class BackOffLock implements Lock {
    private AtomicBoolean state = new AtomicBoolean(false);
    private static final int MIN_DELAY = 1;
    private static final int MAX_DELAY = 1;

    @Override
    public void lock() {
        BackOff backOff = new BackOff(MIN_DELAY, MAX_DELAY);
        while (true) {
            while (state.get()) {
            }
            if (!state.getAndSet(true)) {
                return;
            } else {
                try {
                    backOff.backoff();
                } catch (InterruptedException e) {
                    // ingore
                }
            }
        }

    }

    @Override
    public void unlock() {
        state.set(false);
    }

    private class BackOff {
        final int minDelay, maxDelay;
        int limit;
        final Random random;

        public BackOff(int minDelay, int maxDelay) {
            this.minDelay = minDelay;
            this.maxDelay = maxDelay;
            limit = minDelay;
            random = new Random();
        }

        public void backoff() throws InterruptedException {
            int delay = random.nextInt(limit);
            limit = Math.min(maxDelay, 2 * limit);
            Thread.sleep(delay);
        }
    }
}
