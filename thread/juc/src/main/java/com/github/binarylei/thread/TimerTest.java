package com.github.binarylei.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author leigang
 * @version 2019-05-31
 * @since 2.0.0
 */
public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    long startTime = System.currentTimeMillis() / 1000;
                    TimeUnit.SECONDS.sleep(6);

                    System.out.println(String.format("%s - %s",
                            startTime, System.currentTimeMillis() / 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000);
    }
}
