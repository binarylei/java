package com.github.binarylei._2_4;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayTask implements Delayed {

    private int id;
    //下机截止时间
    private long endTime;
    //时间工具类
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    @Override
    //用来判断是否到了截止时间
    public long getDelay(TimeUnit unit) {
        return endTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        DelayTask task = (DelayTask) o;
        return this.getDelay(timeUnit) > task.getDelay(timeUnit) ? 1 : 0;
    }

    public DelayTask(int id, long endTime) {
        this.id = id;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return endTime;
    }

    public void setTime(long endTime) {
        this.endTime = endTime;
    }
}
