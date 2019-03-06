package com.github.binarylei.collection.other;

import org.junit.Test;

import java.util.StringTokenizer;

/**
 * Enumeration 有 2 个方法 hasMoreElements 和 nextElement
 * @author: leigang
 * @version: 2018-05-05
 */
public class EnumerationTest {

    @Test
    public void test() {
        String str = "java,python,c++";
        StringTokenizer tokenizer = new StringTokenizer(str, ",");
        while (tokenizer.hasMoreElements()) {
            System.out.println(tokenizer.nextElement());
        }
    }
}
