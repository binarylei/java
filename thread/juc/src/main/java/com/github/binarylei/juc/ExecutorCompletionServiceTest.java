package com.github.binarylei.juc;

import org.junit.Test;

import java.util.concurrent.FutureTask;

/**
 * @author leigang
 * @version 2019-06-04
 * @since 2.0.0
 */
public class ExecutorCompletionServiceTest {
    @Test
    public void test() {
        FutureTask f = null;
        while (f.isDone()) {
            // do something
        }
    }
}
