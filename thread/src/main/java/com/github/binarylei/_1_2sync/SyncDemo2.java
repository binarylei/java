package com.github.binarylei._1_2sync;

public class SyncDemo2 {

    static class SupSync {
        protected int i = 10;
        public synchronized void supMethod() {
            System.out.printf("sup父类i=%s\n", i--);
        }
    }

    static class SubSync extends SupSync {
        public synchronized void subMethod() {
            while (i > 0) {
                System.out.printf("sub子类i=%s\n", i--);
                this.supMethod();

            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                new SubSync().subMethod();
            }
        }).start();
    }
}
