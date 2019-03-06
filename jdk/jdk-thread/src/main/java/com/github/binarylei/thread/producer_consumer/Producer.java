package com.github.binarylei.thread.producer_consumer;

/**
 * @author: leigang
 * @version: 2018-05-06
 */
public class Producer implements Runnable {

    private Data data;

    public Producer(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            data.produce(String.format("product-%s", i));
        }
    }
}
