package com.binarylei.concurrent.lock.linkedlist;

import com.binarylei.concurrent.lock.Lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * MCSLock：由每个线程来控制它所自旋的存储单元的
 * <pre>
 * locked= false    true           true
 *           |        |             |
 *          pred -> node -> ... -> tail
 * </pre>
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class MCSLock implements Lock {
    private AtomicReference<QNode> tail;
    ThreadLocal<QNode> myNode;

    public MCSLock() {
        tail = new AtomicReference<>(new QNode());
        myNode = new ThreadLocal<QNode>() {
            @Override
            protected QNode initialValue() {
                return new QNode();
            }
        };

    }

    @Override
    public void lock() {
        QNode qNode = myNode.get();
        QNode pred = tail.getAndSet(qNode);
        if (pred != null) {
            qNode.locked = true;
            pred.next = qNode;
            // wait until predecessor gives up the lock
            while (pred.locked) {
            }
        }

    }

    @Override
    public void unlock() {
        QNode qNode = myNode.get();
        // 可能没有后继结点，也可能正在添加后继结点，只是还未添加成功
        if (qNode.next == null) {
            if (tail.compareAndSet(qNode, null))
                return;
            // wait until successor fills in its next field
            while (qNode.next == null) {
            }
        }
        qNode.next.locked = false;
        qNode.next = null;
    }

    private class QNode {
        private boolean locked = false;
        private QNode next = null;
    }
}
