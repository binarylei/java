package com.github.binarylei._1_2sync;

public class VolatileNotAtomic extends Thread {

    private static volatile int count;
    //private static AtomicInteger count = new AtomicInteger(0);

    public static void addCount() {
        for (int i = 0; i < 1000; i++) {
            count++;
            //count.incrementAndGet();
        }
        System.out.println(count);
    }

    public void run () {
        addCount();
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileNotAtomic[] vna = new VolatileNotAtomic[10];
        for (int i = 0; i < 10; i++) {
            vna[i] = new VolatileNotAtomic();
        }
        for (int i = 0; i < vna.length  ; i++) {
            vna[i].start();
        }
    }
}
