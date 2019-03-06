package com.github.binarylei.collection.set;

import java.util.HashMap;

/**
 * 自定义自己的 HashSet，HashMap 底层是由 HashMap 实现的，key 不能重复
 * @author: leigang
 * @version: 2018-03-22
 */
public class MyHashSet {

    HashMap map;
    private static final Object PRESENT = new Object();

    public MyHashSet(){
        map = new HashMap();
    }

    public int size(){
        return map.size();
    }

    public void add(Object o){
        map.put(o, PRESENT);   //set的不可重复就是利用了map里面键对象的不可重复！
    }

    public static void main(String[] args) {
        MyHashSet s = new MyHashSet();
        s.add("aaa");
        s.add(new String("aaa"));
        System.out.println(s.size());
    }
}
