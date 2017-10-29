package com.github.binarylei._2_4;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

    public static void main(String[] args) {
        //1. LinkedBlockingQueue: 无界阻塞队列
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>();
        q.add("a");
        q.add("b");
        q.add("c");

        System.out.println(q.poll());// => a
    }
}
