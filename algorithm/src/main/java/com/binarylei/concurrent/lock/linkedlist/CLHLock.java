package com.binarylei.concurrent.lock.linkedlist;

import com.binarylei.concurrent.lock.Lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CLHLock：同 ALock 一样，也是先来先服务。CLHLock 使用的空间小，每个线程自旋等待其前驱结点的locked变为false，但如果内存位置比较远，性能会受到影响。
 * <pre>
 * locked= false    true
 *           |        |
 *        myPred   myNode
 *                    |
 *                   tail
 * </pre>
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class CLHLock implements Lock {
    private AtomicReference<QNode> tail;
    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;

    public CLHLock() {
        tail = new AtomicReference<>(new QNode());
        myPred = new ThreadLocal<QNode>() {
            @Override
            protected QNode initialValue() {
                return null;
            }
        };
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
        qNode.locked = true;
        QNode pred = tail.getAndSet(qNode);
        myPred.set(pred);
        while (pred.locked) {
        }

    }

    @Override
    public void unlock() {
        QNode qNode = myNode.get();
        qNode.locked = false;
        myNode.set(myPred.get());
    }

    private class QNode {
        private boolean locked;
    }
}
