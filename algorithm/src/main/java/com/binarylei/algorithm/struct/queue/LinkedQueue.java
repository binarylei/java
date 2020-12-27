package com.binarylei.algorithm.struct.queue;

/**
 * 使用哨兵结点简化编程
 * <pre>
 *    head(哨兵结点不保存数据)      tail
 *     |                           |
 *     e1 —— e2 ——— e3 ——— e4 ——— e5
 * </pre>
 *
 * @author binarylei
 * @version 2020-03-02
 */
public class LinkedQueue implements Queue {

    private Node head;
    private Node tail;
    private int size;

    public LinkedQueue() {
        head = new Node(null, null);
        tail = head;
    }

    @Override
    public Queue enqueue(Object obj) {
        tail.next = new Node(obj, null);
        tail = tail.next;
        size++;
        return this;
    }

    @Override
    public Object dequeue() {
        Node next = head.next;
        if (next == null) {
            return null;
        }
        head = head.next;
        size--;
        return next.item;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node {
        private Object item;
        private Node next;

        private Node(Object item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

}
