package com.github.binarylei.collection.generic;

/**
 * 接口上声明泛型，只能使用在方法上，因为接口中的变量只能是 static 修辞的常量
 * @author: leigang
 * @version: 2018-05-05
 */
public interface InterfaceGeneric<T1, T2> {

    //T1 t;

    T1 test1();
}
