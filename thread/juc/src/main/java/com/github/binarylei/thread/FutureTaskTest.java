package com.github.binarylei.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author leigang
 * @version 2019-05-31
 * @since 2.0.0
 */
public class FutureTaskTest {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(() -> {
            while (!Thread.interrupted())
                System.out.println("1");
        });
        TimeUnit.SECONDS.sleep(1);
        future.cancel(true);

    }

    @Test
    public void test() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }, "Thread-wait-" + i).start();
        }
        TimeUnit.SECONDS.sleep(2);
        Object o = future.get(2, TimeUnit.SECONDS);
    }
}