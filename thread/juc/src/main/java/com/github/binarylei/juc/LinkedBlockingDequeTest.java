package com.github.binarylei.juc;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author leigang
 * @version 2019-05-10
 * @since 2.0.0
 */
public class LinkedBlockingDequeTest {

    @Test
    public void test() {
        LinkedBlockingDeque<Object> deque = new LinkedBlockingDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
    }
}
