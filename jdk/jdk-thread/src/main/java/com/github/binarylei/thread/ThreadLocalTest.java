package com.github.binarylei.thread;

import org.junit.Test;

/**
 * @author: leigang
 * @version: 2018-09-01
 */
public class ThreadLocalTest {

    @Test
    public void test() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                threadLocal.set("qqq");
            }).start();
        }
    }
}
