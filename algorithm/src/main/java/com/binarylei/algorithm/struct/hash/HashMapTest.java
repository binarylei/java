package com.binarylei.algorithm.struct.hash;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author binarylei
 * @version 2020-03-07
 */
public class HashMapTest {

    @Test
    public void test() {
        HashMap map = new HashMap();
        map.put(null, 1);
        System.out.println(map.get(null));
    }
}
