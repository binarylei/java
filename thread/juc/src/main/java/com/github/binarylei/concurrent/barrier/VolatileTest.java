package com.github.binarylei.concurrent.barrier;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author leigang
 * @version 2019-05-11
 * @since 2.0.0
 */
public class VolatileTest {

    private int a = 0, b = 0;
    private volatile int x = 0, y = 0;

    public void thread1() {
        a = 4;
        int tmp2 = b;
        int tmp = x;
        x = b;  // 1
        a = 1;  // 2
    }

    public void thread2() {
        y = a;  // 3
        b = 1;  // 4
    }
}
