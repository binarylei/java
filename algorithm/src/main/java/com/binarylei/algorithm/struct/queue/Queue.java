package com.binarylei.algorithm.struct.queue;

/**
 * @author binarylei
 * @version 2020-03-02
 */
public interface Queue {

    Queue enqueue(Object obj);

    Object dequeue();

    int size();

}
