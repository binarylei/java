package com.binarylei.concurrent.theory;

/**
 * @author binarylei
 * @version 2020-03-17
 */
public class ConsistencyTest {

    private int a;
    private int b;

    private void test() {
        int t1 = a;         // 指令1
        int t2 = b;         // 指令2
        int t3 = t1 + t2;   // 指令3
    }
}
