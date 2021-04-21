package com.binarylei.jvm.clazz;

/**
 * 1. 调用 static 修辞的变量(注意没有 final 修辞)会执行初始化过程，注意只会初始化 static 所在的类
 * 2. 调用 static final 修辞的变量不会执行初始化
 * -XX:+TraceClassLoading 输出所有加载的类（按加载顺序）
 */
public class StaticNoFinalTest {

    public static void main(String[] args) {
        // 1. 输出父类的 static 变量，只初始化父类 Sup
        System.out.println(Sub.sup_string);

        // 2. 输出子类的 static 变量，初始化子类 Sub 的同时会初始化父类
        //System.out.println(Sub.sub_string);
    }
}

class Sup {
    public static String sup_string = "sup_static_no_final_string";

    static {
        System.out.println("Sup static block");
    }
}

class Sub extends Sup {
    public static String sub_string = "sub_static_no_final_string";

    static {
        System.out.println("Sub static block");
    }
}