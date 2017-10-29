package com.github.binarylei._2_4;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {

    public static void main(String[] args) {
        //1. LinkedBlockingQueue: 无界阻塞队列
        PriorityBlockingQueue<PriorityTask> q = new PriorityBlockingQueue<PriorityTask>();

        q.add(new PriorityTask(9));
        q.add(new PriorityTask(1));
        q.add(new PriorityTask(8));

        //不能直接iterator
        /*for (Iterator it = q.iterator(); it.hasNext();) {
            System.out.println(((PriorityTask)it.next()).getId());
        }*/

        try {
            System.out.println(q.take().getId()); //1
            System.out.println(q.take().getId()); //8
            System.out.println(q.take().getId()); //9
            //没有元素就阻塞
            System.out.println(q.take().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
