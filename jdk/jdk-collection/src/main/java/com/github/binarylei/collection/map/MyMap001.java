package com.github.binarylei.collection.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义实现Map的功能！
 * 暂不完美！
 * Map:存放键值对，根据键对象找对应的值对象.键不能重复！
 */
public class MyMap001 {
    Entry[] arr = new Entry[990];
    int size;

    public void put(Object key, Object value) {
        Entry e = new Entry(key, value);
        //解决键值重复的处理
        for (int i = 0; i < size; i++) {
            if (arr[i].key.equals(key)) {
                arr[i].value = value;
                return;
            }
        }

        arr[size++] = e;
    }

    public Object get(Object key) {
        for (int i = 0; i < size; i++) {
            if (arr[i].key.equals(key)) {
                return arr[i].value;
            }
        }
        return null;
    }

    public boolean containsKey(Object key) {
        for (int i = 0; i < size; i++) {
            if (arr[i].key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for (int i = 0; i < size; i++) {
            if (arr[i].value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    class Entry {
        Object key;
        Object value;

        public Entry(Object key, Object value) {
            super();
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int hash = 100;
        int n = 8;
        System.out.println((n - 1) & hash);
        System.out.println(hash % n);
        System.out.println("2222".hashCode());
        System.out.println(Integer.toBinaryString("2222".hashCode()));
        System.out.println(Integer.toBinaryString(2));
        Map map = new HashMap();
        map.put("","");
    }
}
