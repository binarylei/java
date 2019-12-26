package com.github.binarylei.juc;

import java.util.concurrent.SynchronousQueue;

/**
 * @author leigang
 * @version 2019-05-10
 * @since 2.0.0
 */
public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);
        put(queue, 1);
        Thread.sleep(1000L);
        put(queue, 2);
        Thread.sleep(1000L);

        take(queue, 1);
        System.out.println(1);
    }

    private static void put(SynchronousQueue<Integer> queue, Integer value) {
        new Thread(() -> {
            try {
                queue.put(value);
            } catch (InterruptedException e) {
            }
        }, "Thread-put-" + value).start();
    }

    private static void take(SynchronousQueue<Integer> queue, Integer threadId) {
        new Thread(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
            }
        }, "Thread-take-" + threadId).start();
    }


}
