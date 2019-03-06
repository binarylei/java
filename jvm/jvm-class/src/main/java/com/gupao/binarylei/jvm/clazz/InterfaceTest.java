package com.gupao.binarylei.jvm.clazz;

import java.util.UUID;

/**
 * 当一个接口在初始化时，并不要求其父接口完成初始化
 * 例如调用接口中定义的常量(需要在程序运行期确定的例外)：因为接口中的字段默认就是 static final
 * <p>
 * 1. 初始化一个类时并不会初始化其接口
 * 2. 初始化一个接口时并不会初始化其父接口
 *
 * @author leigang
 * @version 2019-03-06
 */
public class InterfaceTest {

    public static void main(String[] args) {
        // 1. 编译期就确定的常量不会导致初始化
        //    当一个接口在初始化时，并不要求其父接口完成初始化
        System.out.println(Intf2.SUB_STRING);
        System.out.println(Intf2.SUP_STRING);

        // 2. 程序运行才能确定的常量会初始化
        //System.out.println(Intf2.INTERFACE_NOT_INIT_VALIDATA);
    }
}

// 接口中是不允许有 static 代码块，但可以定义常量
interface Intf1 {
    public static final String SUP_STRING = "sup_string";
    // 对象会导致初始化，如果初始化必然会输出 interface_not_init_validata
    public static final Object INTERFACE_NOT_INIT_VALIDATA = new Object() {
        {
            System.out.println("interface_not_init_validata");
        }
    };
}

interface Intf2 extends Intf1 {
    public static final String SUB_STRING = "sub_string";
}