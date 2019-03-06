package com.github.binarylei.thread.producer_consumer;

/**
 * @author: leigang
 * @version: 2018-05-06
 */
public class Consumer implements Runnable {
    private Data data;

    public Consumer(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            data.consume();
        }
    }
}
