package com.binarylei.concurrent.theory;

/**
 * @author binarylei
 * @version 2020-03-17
 */
public class AtomicTest {

    private volatile int count;

    public int getAndIncrement() {
        return count++;
    }
}
