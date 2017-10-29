package com.github.binarylei.design.future;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        FutureClient client = new FutureClient();
        Data fd = client.request("请求参数");
        System.out.println("请求已发出");
        Thread.sleep(2000);

        //获取请求的结果
        String ret = fd.getRequest();
        System.out.println(ret); //最终结果
    }
}
