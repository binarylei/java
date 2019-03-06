package com.github.binarylei.collection.map;

import java.util.LinkedList;

/**
 * 自定义Map的升级版：提高查询的效率
 * @author: leigang
 * @version: 2018-03-21
 */
public class MyMap002 {

    LinkedList[]  arr  = new LinkedList[9]; //Map的底层结构就是：数组+链表!
    int size;

    public void put(Object key,Object value) {
        Entry  e = new Entry(key, value);

        int hash = key.hashCode();
        hash = hash < 0 ? -hash : hash;

        int a = hash % arr.length;
        if(arr[a] == null) {
            LinkedList list = new LinkedList();
            arr[a] = list;
            list.add(e);
        } else {
            LinkedList list = arr[a];
            for(int i = 0; i < list.size(); i++) {
                Entry e2 = (Entry) list.get(i);
                if(e2.key.equals(key)){
                    e2.value = value;  //键值重复直接覆盖！
                    return;
                }
            }

            arr[a].add(e);
        }
        //a:1000-->1   b:10000-->13
    }

    public Object get(Object key) {
        int a = key.hashCode() % arr.length;
        if(arr[a] != null) {
            LinkedList list = arr[a];
            for(int i = 0; i < list.size(); i++) {
                Entry e = (Entry) list.get(i);
                if(e.key.equals(key)) {
                    return e.value;
                }
            }
        }

        return null;
    }

    class  Entry {
        Object key;
        Object value;

        public Entry(Object key, Object value) {
            super();
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyMap002 m = new MyMap002();
        m.put("x", "100");
        m.put("y", "200");
        System.out.println(m.get("y"));
    }
}
