package com.github.binarylei.design.producer_consumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<Data> data;

    public Consumer(BlockingQueue<Data> data) {
        this.data = data;
    }
    public Consumer() { }

    @Override
    //消费者消费数据
    public void run() {
        Data d = null;
        try {
            d = this.data.take();
            System.out.printf("当前线程%s消费了%s\n", Thread.currentThread().getName(), d.getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
