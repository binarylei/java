package com.binarylei.algorithm.struct.stack;

/**
 * @author binarylei
 * @version 2020-02-23
 */
public interface Stack {

    Stack push(Object obj);

    Object pop();

    Object peek();

    int size();

    void clear();
}
