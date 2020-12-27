package com.binarylei.concurrent.lock.linkedlist;

import com.binarylei.concurrent.lock.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TOLock：由每个线程来控制它所自旋的存储单元的
 * <pre>
 * locked= false    true           true
 *           |        |             |
 *          pred -> node -> ... -> tail
 * </pre>
 *
 * @author binarylei
 * @version 2020-03-16
 */
public class TOLock implements Lock {
    static QNode AVAIABLE = new QNode();
    private AtomicReference<QNode> tail;
    ThreadLocal<QNode> myNode;

    public TOLock() {
        tail = new AtomicReference<>(null);
        myNode = new ThreadLocal<QNode>() {
            @Override
            protected QNode initialValue() {
                return new QNode();
            }
        };

    }

    @Override
    public void lock() {
    }

    public boolean tryLock(long time, TimeUnit unit) {
        long startTime = System.currentTimeMillis();
        long patience = TimeUnit.MILLISECONDS.convert(time, unit);
        QNode qNode = myNode.get();
        myNode.set(qNode);
        qNode.pred = null;
        QNode myPred = tail.getAndSet(qNode);
        if (myPred == null || myPred.pred == AVAIABLE) {
            return true;
        }

        while (System.currentTimeMillis() - startTime < patience) {
            QNode predPred = myPred.pred;
            if (predPred == AVAIABLE)
                return true;
            else if (predPred != null)
                myPred = predPred;
        }
        if (!tail.compareAndSet(qNode, myPred))
            qNode.pred = myPred;
        return false;
    }

    @Override
    public void unlock() {
        QNode qNode = myNode.get();
        if (!tail.compareAndSet(qNode, null))
            qNode.pred = AVAIABLE;
    }

    private static class QNode {
        private QNode pred = null;
    }
}
