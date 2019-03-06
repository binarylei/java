package com.github.binarylei.collection.iterator;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author: leigang
 * @version: 2018-05-05
 */
public class IteratorTest {

    @Test
    public void test() {
        List<String> list = Arrays.asList(new String[]{"java", "python"});

        for (Iterator it = list.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
    }
}
