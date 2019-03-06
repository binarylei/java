package com.github.binarylei.thread;

/**
 * @author: leigang
 * @version: 2018-05-06
 */
public class DirtyRead {

    private String username;
    private String password;

    public /*synchronized*/ void setValue(String username, String password) {
        this.username = username;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        this.password = password;
        System.out.printf("username=%s; password=%s\n", username, password);
    }

    public /*synchronized*/ void getValue() {
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
        //username=admin; password=null 不加锁，产生脏读
        //username=admin; password=admin 加锁
    }
}
