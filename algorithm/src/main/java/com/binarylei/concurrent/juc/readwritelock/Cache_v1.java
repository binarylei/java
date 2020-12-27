package com.binarylei.concurrent.juc.readwritelock;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author binarylei
 * @version 2020-03-24
 */

class Cache_v1<K, V> {
    final Map<K, V> m = new HashMap<>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    // 读锁
    final Lock r = rwl.readLock();
    // 写锁
    final Lock w = rwl.writeLock();

    // 读缓存
    V get(K key) {
        r.lock();
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }

    // 写缓存
    V put(K key, V value) {
        w.lock();
        try {
            return m.put(key, value);
        } finally {
            w.unlock();
        }
    }


    // ReentrantReadWriteLock 锁不允许升级
    @Test
    public void upgrade(K key) {
        //读缓存
        r.lock();                   // ①
        try {
            Object v = m.get(key);  // ②
            if (v == null) {
                w.lock();
                try {
                    //再次验证并更新缓存
                } finally {
                    w.unlock();
                }
            }
        } finally {
            r.unlock();             // ③
        }
    }


    // ReentrantReadWriteLock 锁允许降级
    @Test
    public void test() {
        // 获取写锁
        w.lock();
        try {
            // 释放写锁前，降级为读锁
            r.lock();   // ①
        } finally {
            // 释放写锁
            w.unlock();
        }
    }

}
