package com.github.binarylei.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author leigang
 * @version 2019-05-30
 * @since 2.0.0
 */
public class ExecutorServiceTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            while (true) System.out.println("go go go");
        });
        executorService.shutdownNow();
        System.out.println("executorService ");
    }
}
