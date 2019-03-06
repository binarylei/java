package com.github.binarylei.collection.generic;

/**
 * 子类继承父类时，泛型的确定
 * 1. 方法上泛型的类型由父类而定
 * 2. 属性上泛型的类型由据的类而定，即属性在父类中定义由父类确定，在子类中由子类确定。
 * @author: leigang
 * @version: 2018-05-05
 */

//1. 父类指定泛型，则 SubGeneric 中指定的泛型必须 >= 父类，否则报错
public class SubGeneric<T, T1> extends ClassGeneric<T> {
    @Override
    public T test(T t) {
        return super.test(t);
    }
}

//2. 父类指定部分泛型，则 SubGeneric 中指定的泛型必须 >= 父类，否则报错
class SubGeneric2<T1, T2> extends ClassGeneric<String> {

    T1 t;

    @Override
    public String test(String s) {
        return super.test(s);
    }

    public static void main(String[] args) {
        SubGeneric2<String, String> sub = new SubGeneric2();
        String t = sub.t;
    }
}

//3. 父类擦除泛型，则默认为 Object
class SubGeneric3<T3, T4> extends ClassGeneric {

    @Override
    public Object test(Object t) {
        return super.test(t);
    }
}
