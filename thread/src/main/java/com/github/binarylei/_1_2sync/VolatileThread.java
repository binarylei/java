package com.github.binarylei._1_2sync;

public class VolatileThread extends Thread {

    //volatile修辞的变量，变量发生变化时会强制性从主线程栈中读取该变量
    private volatile boolean isRuning = true;

    public void setIsRuning(boolean isRuning) {
        this.isRuning = isRuning;
        System.out.println("变量isRuning设置成功");
    }

    public void run () {
        while (isRuning) {
           // do ...
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileThread vt = new VolatileThread();
        vt.start();
        Thread.sleep(1000);
        //主线程调制的变量isRuning在线程vt中生效，即实现了可见性
        vt.setIsRuning(false);
    }
}
