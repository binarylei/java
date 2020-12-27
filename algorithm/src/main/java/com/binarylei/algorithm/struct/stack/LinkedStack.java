package com.binarylei.algorithm.struct.stack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

/**
 * 基于链表的栈
 *
 * @author binarylei
 * @version 2020-02-23
 */
public class LinkedStack implements Stack {
    private Node top;
    private int size;

    @Override
    public Stack push(Object obj) {
        Node newNode = new Node(obj, null);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        size++;
        return this;
    }

    @Override
    public Object pop() {
        if (top == null) return null;

        Node tmp = top;
        top = top.next;
        size--;
        return tmp.item;
    }

    @Override
    public Object peek() {
        return top == null ? null : top.item;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void clear() {
        top = null;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private static class Node {
        private Object item;
        private Node next;
    }


    @Test
    public void testLinkedStack() {
        Stack stack = new LinkedStack();
        Assert.assertEquals(0, stack.size());
        Assert.assertEquals(null, stack.pop());

        stack.push(1);
        Assert.assertEquals(1, stack.size());
        Assert.assertEquals(1, stack.pop());

        stack.push(1).push(2);
        Assert.assertEquals(2, stack.size());
        Assert.assertEquals(2, stack.pop());
        Assert.assertEquals(1, stack.pop());
        Assert.assertEquals(null, stack.pop());
    }
}
