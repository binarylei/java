package com.github.binarylei.jvm.clazz;

/**
 * 对于数组实例来说，其类开是由 JVM 在运行期动态生成的，表示为 [Lxx.xx
 * 动态生成的类型，其父类型是 Object
 * <p>
 * 助记符
 * anewarrys: 表示创建一个引用类型（类、接口）的数组，并将其引用值压入栈顶
 * newarrys: 表示创建一个原始类型（int/long/char/...）的数组，并将其引用值压入栈顶
 *
 * @author leigang
 * @version 2019-03-05
 */
public class StaticFinalArrayTest {

    public static void main(String[] args) {
        // 1. new 初始化
        //StaticFinalArray obj = new StaticFinalArray();

        // 2. new StaticFinalArray[] 不会初始化
        StaticFinalArray[] arrays = new StaticFinalArray[]{};
        System.out.println(arrays.getClass().getName());
        System.out.println(arrays.getClass().getSuperclass().getName());
    }
}

class StaticFinalArray {

    public static final String[] ARR = new String[]{};

    static {
        System.out.println("init");
    }
}
