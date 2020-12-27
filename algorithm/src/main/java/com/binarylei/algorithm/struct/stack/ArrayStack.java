package com.binarylei.algorithm.struct.stack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.Assert;

/**
 * 固定长度的栈，不支持动态扩容
 *
 * @author binarylei
 * @version 2020-02-23
 */
public class ArrayStack implements Stack {
    private Object[] array;
    // 数组位置
    private int pos;

    public ArrayStack() {
        this.array = new Object[1024];
    }

    public ArrayStack(int capcity) {
        this.array = new Object[capcity];
    }

    @Override
    public Stack push(Object obj) {
        if (pos >= array.length) {
            throw new IllegalArgumentException("array is full");
        }
        array[pos++] = obj;
        return this;
    }

    @Override
    public Object pop() {
        if (pos == 0) return null;

        Object value = array[--pos];
        array[pos] = null;
        return value;
    }

    @Override
    public Object peek() {
        return array[pos - 1];
    }

    @Override
    public int size() {
        return pos;
    }

    @Override
    public void clear() {
        for (int i = 0; i < pos; i++) {
            array[i] = null;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private static class Node {
        private Object item;
        private Node next;
    }


    public static void main(String[] args) {
        Stack stack = new ArrayStack();
        Assert.assertEquals(0L, stack.size());
        Assert.assertEquals(null, stack.pop());

        stack.push(1);
        Assert.assertEquals(1L, stack.size());
        Assert.assertEquals(1, stack.pop());

        stack.push(1).push(2);
        Assert.assertEquals(2L, stack.size());
        Assert.assertEquals(2, stack.pop());
        Assert.assertEquals(1, stack.pop());
        Assert.assertEquals(null, stack.pop());
    }
}
