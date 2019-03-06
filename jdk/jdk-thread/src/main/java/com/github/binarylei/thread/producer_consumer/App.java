package com.github.binarylei.thread.producer_consumer;

/**
 * 信号灯法解决多个线程挣抢同一份资源的情况
 * @author: leigang
 * @version: 2018-05-06
 */
public class App {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(new Producer(data)).start();
        new Thread(new Consumer(data)).start();
    }
}
