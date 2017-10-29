package com.github.binarylei.design.future;

public class RealData implements Data {
    public String ret;

    public RealData(String questString) {
        //模拟处理数据过程，耗时10s
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            ;
        }
        ret = "最终结果";
    }

    public String getRequest() {
        return ret;
    }
}
