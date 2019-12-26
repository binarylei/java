package com.github.binarylei.concurrent;

/**
 * @author leigang
 * @version 2019-05-19
 * @since 2.0.0
 */
public class ConcurrencyTest {

    /**
     * 执行次数
     */
    private static final long count = 10000L;

    public static void main(String[] args) throws InterruptedException {
        //并发计算
        concurrency();
        //单线程计算
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int a = operate1();
            System.out.println(a);
        });
        thread.start();

        int b = operate2();
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = operate1();
        int b = operate2();
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }

    private static int operate1() {
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        return a;
    }

    private static int operate2() {
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        return b;
    }
}
