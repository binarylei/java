package com.github.binarylei.collection.generic;

/**
 * 1. 类上声明泛型：一般使用 T(type) K(key) V(value) E(elemetn)
 * 2. 方法上声明泛型：在返回值类型前
 * 3. 不能用在 static 修辞的变量上，因为泛型的类型在使用的时候确定，而 static 的类型在编译的时候确定
 * @author: leigang
 * @version: 2018-05-05
 */
public class ClassGeneric<T> {

    // 变量上使用泛型
    T t;

    // 不能用在 static 修辞的变量上使用泛型
    //static T t1;

    // 方法上使用泛型
    public T test(T t) {
        return (T) null;
    }

    // 方法上声明泛型，使用时确定
    public <T1> void test2(T1 t) {
    }

    // 方法上声明泛型，使用时确定
    public <T1> T1 test3(T1 t) {
        return t;
    }
}
