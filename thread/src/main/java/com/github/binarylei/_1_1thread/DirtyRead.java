package com.github.binarylei._1_1thread;

public class DirtyRead {
    private String username;
    private String password;

    public synchronized void setValue(String username, String password) {
        this.username = username;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        this.password = password;
        System.out.printf("username=%s; password=%s\n", username, password);
    }

    public synchronized void getValue() {
        System.out.printf("username=%s; password=%s\n", username, password);
    }

    public static void main(String[] args) throws InterruptedException {
        final DirtyRead dirtyRead = new DirtyRead();
        new Thread(new Runnable() {
            public void run() {
                dirtyRead.setValue("admin", "admin");
            }
        }).start();
        Thread.sleep(1000);
        dirtyRead.getValue();
    }
}
