package com.gupao.binarylei.jvm;

import java.io.IOException;

public class Test1 {

    // -Xms5m -Xms20m  -XX:+PrintGCDetails
    public static void main(String[] args) throws IOException {
        System.in.read();
        allocatMemory(0);
        allocatMemory(1 * 1024 * 1024);
        allocatMemory(4 * 1024 * 1024);
    }

    public static void allocatMemory(int size) {
        byte[] b = new byte[size];
        System.out.println(String.format("分配 %.2fM 后内存情况如下：maxMemory=%.2fM, totalMemory=%.2fM, freeMemory=%.2fM",
                size / ((double) 1024 * 1024),
                Runtime.getRuntime().maxMemory() / ((double) 1024 * 1024),
                Runtime.getRuntime().totalMemory() / ((double) 1024 * 1024),
                Runtime.getRuntime().freeMemory() / ((double) 1024 * 1024)));
    }
}
