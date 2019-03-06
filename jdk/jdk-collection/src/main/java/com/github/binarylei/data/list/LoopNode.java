package com.github.binarylei.data.list;

/**
 * 循环单链表
 * <pre>
 *  --------------      --------------
 * | value | next | -> | value | next |
 *  --------------      --------------
 * </pre>
 *
 * @author: leigang
 * @version: 2018-12-07
 */
public class LoopNode {

    private Object value;
    // 循环单链表
    private LoopNode next = this;

    public LoopNode(Object value) {
        this.value = value;
    }

    public void next(LoopNode next) {
        next.next = this.next;
        this.next = next;
    }

    public LoopNode next() {
        return next;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
