package com.github.binarylei._2_3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        list.add("a");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(0));
        }

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("k1", "v1");

    }

}
