package com.github.binarylei;

public class RunableService implements Runnable {
    @Override
    public void run() {
        System.out.println("service run...");
    }
}
