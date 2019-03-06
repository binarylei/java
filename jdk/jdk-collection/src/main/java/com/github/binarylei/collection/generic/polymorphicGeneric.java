package com.github.binarylei.collection.generic;

/**
 * 泛型没有多态
 * Java 的多态体现在两个方面：1. 参数多态，2. 返回值类型多态
 * @author: leigang
 * @version: 2018-05-05
 */
public class polymorphicGeneric {

    public static void main(String[] args) {
        polymorphicGeneric polymorphicGeneric = new polymorphicGeneric();

        //polymorphicGeneric.run(new ClassGeneric<String>());
    }

    public ClassGeneric<Object> run() {
        // Inconvertible type
        //return (ClassGeneric<Object>) new ClassGeneric<String>();
        return null;
    }

    public void run(ClassGeneric<Object> obj) {
    }

    // jdk的两种多态形式
    public Object test() {
        return 3;
    }

    public void test(Object obj) {
    }
}
