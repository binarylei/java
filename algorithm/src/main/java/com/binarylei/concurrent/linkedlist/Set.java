package com.binarylei.concurrent.linkedlist;

/**
 * @author binarylei
 * @version 2020-03-15
 */
interface Set<E> {
    boolean add(E t);

    boolean remove(E t);

    boolean contain(E t);
}
