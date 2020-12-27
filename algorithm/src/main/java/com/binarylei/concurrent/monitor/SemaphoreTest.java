package com.binarylei.concurrent.monitor;

import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * @author binarylei
 * @version 2020-03-20
 */
public class SemaphoreTest {

    @Test
    public void testMutex() throws InterruptedException {
        Semaphore mutex = new Semaphore(1);
        mutex.acquire();
        // do something
        mutex.release();
    }

    @Test
    public void testConditionA() throws InterruptedException {
        Semaphore condition = new Semaphore(0);
        // threadA
        condition.acquire();

        // threadB
        condition.release();
    }

    private class BoundedBuffer {
        private int n = 100;
        private Semaphore mutex = new Semaphore(1);
        private Semaphore notFull = new Semaphore(n);
        private Semaphore notEmpty = new Semaphore(0);

        public  void product() throws InterruptedException {
            notFull.acquire();      // 缓冲区满时，生产者线程必须等待
            mutex.acquire();
            // ...
            mutex.release();
            notEmpty.release();      // 唤醒等待的消费者线程
        }

        public void consume() throws InterruptedException {
            notEmpty.acquire();      // 缓冲区空时，消费都线程等待
            mutex.acquire();
            // ...
            mutex.release();
            notFull.release();      // 唤醒等待的生产者线程
        }
    }


    private class Semaphore1 {
        private int sem;
        private WaitQueue q;

        void P() {
            sem--;
            if (sem < 0) {
                // add this thread t to q;
//                block(t);
            }
        }

        void V() {
            sem++;
            if (sem <= 0) {
                // remove a thread t from q;
//                wakeup(t);
            }
        }

        @Test
        public void block() {

        }
    }

    private class WaitQueue {
    }

}
