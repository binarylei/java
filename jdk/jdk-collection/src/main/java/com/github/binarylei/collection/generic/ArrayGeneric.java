package com.github.binarylei.collection.generic;

import java.util.ArrayList;

/**
 * 注意没有泛型数组，看 ArrayList 如何处理泛型
 * @author: leigang
 * @version: 2018-05-05
 */
public class ArrayGeneric<T> {

    //T[] t = new T[3];

    Object[] elemetnts = new Object[4];

    public T[] getAll() {
        return (T[]) elemetnts;
    }

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        list.get(0);
    }
}
