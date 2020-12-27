package com.binarylei.algorithm.struct.queue;

/**
 * <pre>
 *    head                            tail
 *     |                               |
 *     e1 —— e2 ——— e3 ——— e4 ——— e5  next
 * </pre>
 *
 * @author binarylei
 * @version 2020-03-02
 */
public class ArrayQueue implements Queue {

    private Object[] array;
    private int capcity;
    // 头结点指向
    private int head;
    private int tail;

    public ArrayQueue() {
        this.capcity = 1024;
        this.array = new Object[this.capcity];
    }

    public ArrayQueue(int capcity) {
        this.capcity = capcity;
        this.array = new Object[capcity];
    }

    /**
     * tail 指向数组最后位置时，需要触发扩容或数组搬移
     * 1. head!=0 说明数组还有剩余的空间，将 head 搬运到队列 array[0]
     * 2. head==0  说明数组没有剩余的空间，扩容
     */
    @Override
    public Queue enqueue(Object obj) {
        if (tail == capcity) {
            if (head == 0) {
                resize();
            } else {
                rewind();
            }
        }
        array[tail++] = obj;
        return this;
    }

    /**
     * 将 head 搬运到队列 array[0]
     */
    private void rewind() {
        // 头结点是 0 说明已经搬运完成
        if (head == 0) {
            return;
        }
        for (int i = head; i < tail; i++) {
            array[i - head] = array[i];
            array[i] = null;
        }
        tail -= head;
        head = 0;
    }

    /**
     * 扩容
     */
    private void resize() {
        // 头结点还是 0 说明还有空间
        if (head != 0) {
            return;
        }
        int oldCapcity = this.capcity;
        int newCapcity = this.capcity * 2;
        Object[] newArray = new Object[newCapcity];
        for (int i = 0; i < oldCapcity; i++) {
            newArray[i] = array[i];
        }
        this.capcity = newCapcity;
        this.array = newArray;
    }

    @Override
    public Object dequeue() {
        if (head == tail) {
            return null;
            //throw new IllegalArgumentException("queue hasn't remaining element");
        }
        Object obj = array[head];
        array[head] = null;
        head++;
        return obj;
    }

    @Override
    public int size() {
        return tail - head;
    }

}
