package com.github.binarylei.thread.singleton;

/**
 * @author: leigang
 * @version: 2018-05-05
 */
public class JVM {

    private static JVM instance;

    private JVM() throws InterruptedException {
        Thread.sleep(2 * 1000);
    }

    public static JVM newInstance() throws InterruptedException {
        if (instance == null) {
            instance = new JVM();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(JVM.newInstance());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
