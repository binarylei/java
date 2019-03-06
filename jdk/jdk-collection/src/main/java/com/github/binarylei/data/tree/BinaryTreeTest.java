package com.github.binarylei.data.tree;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: leigang
 * @version: 2018-12-09
 */
public class BinaryTreeTest {

    @Test
    public void test() {
        BinaryTree binTree = new BinaryTree();
        TreeNode<Integer> root = new TreeNode<>(2);
        root.setLeft(new TreeNode<>(1));
        root.setRight(new TreeNode<>(3));
        binTree.setRoot(root);

        root.getLeft().setLeft(new TreeNode(4));
        root.getLeft().setRight(new TreeNode(5));
        root.getRight().setLeft(new TreeNode(6));
        root.getRight().setRight(new TreeNode(7));

        binTree.frontShow();

        binTree.delete(1);
        binTree.frontShow();
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayBinaryTree binTree = new ArrayBinaryTree(arr);
        binTree.preOrder();
    }

    @Test
    public void test3() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayBinaryTree binTree = new ArrayBinaryTree(arr);
        binTree.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
