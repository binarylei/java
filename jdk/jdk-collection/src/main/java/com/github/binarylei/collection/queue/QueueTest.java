package com.github.binarylei.collection.queue;

import com.sun.jmx.remote.internal.ArrayQueue;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 队列(queue)是一种常用的数据结构，可以将队列看做是一种特殊的线性表，该结构遵循的先进先出原则。
 * Java 中，LinkedList 实现了 Queue 接口,因为 LinkedList 进行插入、删除操作效率较高
 *
 * 双向队列(Deque)，是 Queue 的一个子接口，双向队列是指该队列两端的元素既能入队(offer)也能出队(poll)
 * 如果将 Deque 限制为只能从一端入队和出队，则可实现栈的数据结构。对于栈而言，有入栈(push)和出栈(pop)，遵循先进后出原则
 * @author: leigang
 * @version: 2018-05-05
 */
public class QueueTest {

    /**
     * 1. offer(E e): 将元素追加到队列末尾,若添加成功则返回 true。
     * 2. E poll(): 从队首删除并返回该元素。
     * 3. E peek(): 返回队首元素，但是不删除
     */
    @Test
    public void QueueTest() {
        Queue<String> queue = new LinkedList<>();

        queue.offer("java");
        queue.offer("python");
        queue.offer("c++");

        System.out.println(queue.peek());
        while(queue.size() > 0) {
            System.out.println(queue.poll());
        }
    }

    /**
     * 1. void push(E e): 将给定元素”压入”栈中。存入的元素会在栈首。即:栈的第一个元素
     * 2. E pop(): 将栈首元素删除并返回
     */
    @Test
    public void DequeTest() {
        Deque<String> queue = new LinkedList<>();

        queue.push("java");
        queue.push("python");
        queue.push("c++");

        System.out.println(queue.peek());
        while(queue.size() > 0) {
            System.out.println(queue.pop());
        }
    }
}
