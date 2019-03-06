package com.gupao.binarylei.jvm.clazz;

import java.util.UUID;

/**
 * 当一个常量的值并非编译期间可以确定的，那么其值就不会被放到调用类的常量，
 * 这里在程序运行时，会导致主动使用这个常量所在的类，显然会导致这个类被初始化
 *
 * @author leigang
 * @version 2019-03-05
 */
public class StaticFinalDynamicTest {

    public static void main(String[] args) {
        System.out.println(StaticFinalDynamic.RANDOM);
    }
}

class StaticFinalDynamic {
    public static final String RANDOM = UUID.randomUUID().toString();

    static {
        System.out.println(StaticFinalDynamic.class.getSimpleName());
    }
}
