package com.github.binarylei.design.future;

public class FutureClient {

    public Data request(final String questString) {
        //1. 代理对象()先返回发送请求的客户端，告诉他请求已经接收到，可以做其它事情了
        final FutureData futureData = new FutureData();
        //2. 启动一个线程，去加载真实的数据，传递给这个代理对象
        new Thread(new Runnable() {
            public void run() {
                //3. 新的线程可以慢慢加载真实对象，然后传递给代理对象
                RealData realData = new RealData(questString);
                futureData.setRealData(realData);
            }
        }).start();

        return futureData;
    }
}
