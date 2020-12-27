package com.binarylei.algorithm.struct.queue;

/**
 * 数组循环队列，入队时当 head=tail 需要扩容，出队时当 head=tail 则说明没有元素
 * <pre>
 *    head                        tail
 *     |                           |
 *     e1 —— e2 ——— e3 ——— e4 ——— next
 * </pre>
 *
 * @author binarylei
 * @version 2020-03-02
 */
public class ArrayCircularQueue implements Queue {

    private Object[] array;
    private int capcity;
    // 头结点指向
    private int head;
    private int tail;

    public ArrayCircularQueue() {
        this.capcity = 1024;
        this.array = new Object[this.capcity];
    }

    public ArrayCircularQueue(int capcity) {
        this.capcity = capcity;
        this.array = new Object[capcity];
    }

    @Override
    public Queue enqueue(Object obj) {
        array[tail] = obj;
        if ((tail = (tail + 1) % capcity) == head) {
            resize();
        }
        return this;
    }

    @Override
    public Object dequeue() {
        if (head == tail) {
            return null;
        }
        Object obj = array[head];
        array[head] = null;
        head = (head + 1) % capcity;
        return obj;
    }

    // 不扩容，要先判断能否往数组中添加元素
    public Queue enqueue2(Object obj) {
        if ((tail + 1) % capcity == head) return this;
        array[tail] = obj;
        tail = (tail + 1) % capcity;
        return this;
    }

    /**
     * 扩容
     */
    private void resize() {
        // 说明还有空间
        if (head != tail) {
            return;
        }
        int oldCapcity = this.capcity;
        int newCapcity = this.capcity * 2;
        Object[] newArray = new Object[newCapcity];
        for (int i = head; i < oldCapcity; i++) {
            newArray[i - head] = array[i];
        }
        for (int i = 0; i < head; i++) {
            newArray[capcity - head + i] = array[i];
        }
        this.capcity = newCapcity;
        this.array = newArray;
        this.head = 0;
        this.tail = oldCapcity;
    }


    @Override
    public int size() {
        return tail - head;
    }

}
