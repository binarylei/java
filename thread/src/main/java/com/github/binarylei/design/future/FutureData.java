package com.github.binarylei.design.future;

public class FutureData implements Data {
    //真实数据
    public RealData realData;
    //真实数据是否已经加载完成，程序启动时还未加载，默认傎为false
    public boolean isReading = false;

    public synchronized void setRealData (RealData realData) {
        if(isReading) {
            return;
        }
        isReading = true;
        this.realData = realData;
        notify();

    }

    public synchronized String getRequest() {
        //数据还未准备好，阻塞线程
        while (!isReading) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("程序正在处理数据，主稍等...");
            }
        }
        return this.realData.getRequest();
    }
}
