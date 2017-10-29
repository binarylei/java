package com.github.binarylei._1_2sync;

public class SyncException {

    private int i = 0;

    public synchronized void operation() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.printf("%s i=%s\n", Thread.currentThread().getName(), ++i);
                if (i == 10) {
                    Integer.parseInt("a");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.printf("log error i=%s\n", i);
                throw new RuntimeException();
                //continue;
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                new SyncException().operation();
            }
        }).start();
    }
}
