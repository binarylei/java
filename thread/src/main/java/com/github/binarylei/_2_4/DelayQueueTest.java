package com.github.binarylei._2_4;

import java.util.concurrent.DelayQueue;

public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        final DelayQueue<DelayTask> q = new DelayQueue<DelayTask>();

        q.add(new DelayTask(8,  8 * 1000 + System.currentTimeMillis()));

        q.add(new DelayTask(2,  2 * 1000 + System.currentTimeMillis()));
        q.add(new DelayTask(3,  3 * 1000 + System.currentTimeMillis()));
        q.add(new DelayTask(5,  5 * 1000 + System.currentTimeMillis()));

        /*Iterator it = q.iterator();
        while (it.hasNext()) {
            DelayTask next = (DelayTask) it.next();
            System.out.println(next.getId());
        }*/

        while (true) {
            try {
                DelayTask take = q.take();
                System.out.println(take.getId() + " , " + take.getTime());
            } catch (Exception e) {

            }
        }

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println(q.take().getId());
                    } catch (Exception e) {

                    }
                }
            }
        });*/
    }
}
