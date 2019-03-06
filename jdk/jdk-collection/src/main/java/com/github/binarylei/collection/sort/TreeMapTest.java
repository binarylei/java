package com.github.binarylei.collection.sort;

import org.junit.Test;

import java.util.TreeMap;

/**
 * TreeMap 类似 TreeSet
 * @author: leigang
 * @version: 2018-05-05
 */
public class TreeMapTest {

    @Test
    public void test1() {
        TreeMap<UserBean, String> treeMap = new TreeMap<>();

        treeMap.put(new UserBean(5), "user1");
        treeMap.put(new UserBean(4), "user1");
        treeMap.put(new UserBean(7), "user1");

        System.out.println(treeMap);
    }

}
