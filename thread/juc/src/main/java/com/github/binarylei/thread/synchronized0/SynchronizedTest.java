package com.github.binarylei.thread.synchronized0;

/**
 * @author leigang
 * @version 2019-05-18
 * @since 2.0.0
 */
public class SynchronizedTest {

    // 作用于类级别
    public synchronized static void testClass() {
        System.out.println("synchronized test!!!");
    }

    // 作用于方法级别
    public synchronized void testMethod() {
        System.out.println("synchronized test!!!");
    }

    // 作用于代码块级别
    public void testBlock() {
        synchronized (this) {
            System.out.println("synchronized test!!!");
        }
    }
}
