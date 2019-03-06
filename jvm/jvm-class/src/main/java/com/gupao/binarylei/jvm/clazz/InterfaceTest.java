package com.gupao.binarylei.jvm.clazz;

/**
 * 初始化接口时不必初始化其父接口
 *
 * @author leigang
 * @version 2019-03-06
 */
public class InterfaceTest {

    public static void main(String[] args) {
        System.out.println(Intf2.SUB_STRING);
    }
}

// 接口中是不允许有 static 代码块，但可以定义常量
interface Intf1 {
    public static final String SUP_STRING = "sup_string";
}

interface Intf2 extends Intf1 {
    public static final String SUB_STRING = "sup_string";
}