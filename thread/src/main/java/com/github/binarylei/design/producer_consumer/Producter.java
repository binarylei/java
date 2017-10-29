package com.github.binarylei.design.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producter implements Runnable {

    private BlockingQueue<Data> data;
    //用于生成id
    private static AtomicInteger count = new AtomicInteger(0);

    public Producter(BlockingQueue<Data> data) {
        this.data = data;
    }

    public Producter() { }

    @Override
    //生产者生产数据
    public void run() {
        try {
            synchronized (count) {
                Thread.sleep(2000);
                int id = count.incrementAndGet();
                if (!this.data.offer(new Data(id, "data-" + id), 2, TimeUnit.SECONDS)) {

                    System.out.printf("当前线程%s生产data-%s失败\n", Thread.currentThread().getName(), id);
                } else {
                    System.out.printf("当前线程%s生产了data-%s\n", Thread.currentThread().getName(), id);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
