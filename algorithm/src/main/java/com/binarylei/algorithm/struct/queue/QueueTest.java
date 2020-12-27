package com.binarylei.algorithm.struct.queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author binarylei
 * @version 2020-03-02
 */
public class QueueTest {

    @Test
    public void testArrayQueue() {
        testQueue(new ArrayQueue(2));
    }

    @Test
    public void testLinkedQueue() {
        testQueue(new LinkedQueue());
    }

    @Test
    public void testArrayCircularQueue() {
        testQueue(new ArrayCircularQueue(2));
    }

    private void testQueue(Queue queue) {
        queue.enqueue(1).enqueue(2).enqueue(3);

        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(1, queue.dequeue());
        queue.enqueue(4).enqueue(5);
        Assert.assertEquals(2, queue.dequeue());
        Assert.assertEquals(3, queue.dequeue());
        Assert.assertEquals(4, queue.dequeue());
        Assert.assertEquals(5, queue.dequeue());
        Assert.assertEquals(0, queue.size());
        queue.enqueue(6);
    }


}
