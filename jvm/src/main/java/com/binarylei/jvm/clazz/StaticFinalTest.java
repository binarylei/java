package com.binarylei.jvm.clazz;

/**
 * 常量在编译阶段会存入到调用这个常量的方法所在类的常量池中
 * 本质上，调用类并没有直接引用到定义常量的类，因此并不会触发初始化
 * 也就是说编译完成后 StaticFinalClassTest 和 StaticFinalClass 没有任何关系了
 * 甚至，我们可以在编译完成后将 StaticFinalClass 删除
 * <p>
 * 反编译后的结果如下：
 * 3: ldc           #4                  // String static_final_string
 * ldc 表示将 int float String 类型的常量从常量池中推送到栈顶
 * bipush 表示将单字节(-128~127)的常量值推送至栈顶
 * sipush 表示将短整形常量值(-32768~32767)的常量值推送至栈顶
 * iconst_m1~iconst_0~iconst_5 表示将 int 类型的 -1~5 推送至栈顶(java 中 ICONST 加载)
 *
 * @author leigang
 * @version 2019-03-05
 */
public class StaticFinalTest {

    public static void main(String[] args) {
        System.out.println(StaticFinal.static_final_string);
        System.out.println(StaticFinal.static_no_final_String);
    }
}

class StaticFinal {

    // 1. 常量，不会触发初始化
    public static final String static_final_string = "static_final_string";

    // 2. 非常量，会触发初始化
    public static String static_no_final_String = "static_no_final_string";

    static {
        System.out.println("init");
    }

}
