package com.github.binarylei.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author leigang
 * @version 2019-05-20
 * @since 2.0.0
 */
public class HashMapTest {

    @Test
    public void test1() {
        Map<Integer, Integer> map = new HashMap<>();
        map.computeIfAbsent(1, t -> t + 1);
        map.computeIfAbsent(1, t -> t + 2);
        map.computeIfAbsent(2, t -> null);
        map.put(2, null);
        map.computeIfAbsent(2, t -> t + 1);

        System.out.println(map);
    }

    public static void main(String[] args) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                new Thread(() ->
                        map.put(UUID.randomUUID().toString(), null)
                ).start();
            }
        });
        thread.start();
        thread.join();
    }


    @Test
    public void test3() {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("a");
        list.add("b");
        list.get(0);
    }

    @Test
    public void testLinkedBlockingQueue() {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    }

    @Test
    public void testPriorityBlockingQueue() {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(8);
        queue.offer(1);
        queue.offer(4);
        queue.offer(2);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);

    }
}
