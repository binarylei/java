package com.github.binarylei._1_1thread;

public class MutiThread {

    private /*static*/ int num = 0;

    //static synchronized：表示类级别的锁
    public /*static*/ synchronized void printNum (String tag) {
        try {
            if("a".equalsIgnoreCase(tag)) {
                System.out.printf("tag %s 设置成功\n", tag);
                num = 100;
                Thread.sleep(1000);
            } else {
                System.out.printf("tag %s 设置成功\n", tag);
                num = 200;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("tag %s , num=%s\n", tag, num);
    }

    public static void main(String[] args) {
        /**
         * synchronized：对象锁，两个对象，线程获得的就是两个不同的锁，互不影响
         * static synchronized：表示类级别的锁，即便多个对象也是相同的锁
         */
        final MutiThread m1 = new MutiThread();
        final MutiThread m2 = new MutiThread();
        new Thread(new Runnable() {
            public void run() {
                m1.printNum("a");
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                m1.printNum("b");
            }
        }).start();
    }
}
