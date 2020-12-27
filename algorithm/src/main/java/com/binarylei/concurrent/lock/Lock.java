package com.binarylei.concurrent.lock;

/**
 * @author binarylei
 * @version 2020-03-16
 */
public interface Lock {

    void lock();

    void unlock();
}
