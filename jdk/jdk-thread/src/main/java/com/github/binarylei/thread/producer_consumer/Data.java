package com.github.binarylei.thread.producer_consumer;

/**
 * @author: leigang
 * @version: 2018-05-06
 */
public class Data {

    private String data;

    // flag = true  生产者生产，消费者等待，生产完毕后通知消费者消费
    // flag = false 消费者消费，生产者等待，消费完毕后通知生产者生产
    private boolean flag = true;

    public synchronized void consume() {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                ;
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            ;
        }

        notify();
        System.out.println("消费者消费：" + getData());
        flag = true;
    }

    public synchronized void produce(String data) {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                ;
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            ;
        }

        notify();
        System.out.println("生产者生产：" + data);
        setData(data);
        flag = false;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
