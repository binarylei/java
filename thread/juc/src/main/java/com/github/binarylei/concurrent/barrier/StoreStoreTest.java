package com.github.binarylei.concurrent.barrier;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author leigang
 * @version 2019-05-11
 * @since 2.0.0
 */
public class StoreStoreTest {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            StoreStoreTest reordering = new StoreStoreTest();
            Thread thread1 = new Thread(reordering::reader);
            Thread thread2 = new Thread(reordering::writer);

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        }
    }

    int a = 0;
    boolean flag = false;

    // storestore
    public void writer() {
        a = 1;          // 1
        flag = true;    // 2
    }

    public void reader() {
        if (flag) {                 // 3
            assert a == 1 : "a=0";  // 4
        }
    }

}
