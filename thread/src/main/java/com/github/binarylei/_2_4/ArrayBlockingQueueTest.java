package com.github.binarylei._2_4;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        //1. ArrayBlockingQueue需要指定长度
        ArrayBlockingQueue<String> q = new ArrayBlockingQueue<String>(20);
        q.add("a");
        q.add("b");
        q.add("c");

        System.out.println(q.poll());// => a
    }
}
