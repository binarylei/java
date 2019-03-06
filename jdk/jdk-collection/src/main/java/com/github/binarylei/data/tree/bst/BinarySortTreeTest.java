package com.github.binarylei.data.tree.bst;

import org.junit.Test;

/**
 * @author: leigang
 * @version: 2018-12-11
 */
public class BinarySortTreeTest {

    @Test
    public void test() {
        Integer[] arr = {3, 2, 7, 6, 1};
        BinarySortTree<Integer> bst = new BinarySortTree<>();
        for (Integer i : arr) {
            bst.add(new BinarySortTree.Node(i));
        }
        bst.midOrder();
    }

}
