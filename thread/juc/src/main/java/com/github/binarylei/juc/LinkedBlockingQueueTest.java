package com.github.binarylei.juc;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author leigang
 * @version 2019-05-10
 * @since 2.0.0
 */
public class LinkedBlockingQueueTest {

    @Test
    public void test() {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        queue.add(1);
        queue.add(2);
    }
}
