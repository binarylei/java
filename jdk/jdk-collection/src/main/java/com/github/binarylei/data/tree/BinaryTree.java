package com.github.binarylei.data.tree;

/**
 * @author: leigang
 * @version: 2018-12-09
 */
public class BinaryTree<E> {

    private TreeNode root;

    public void frontShow() {
        if (root != null) {
            root.preOrder();
            System.out.println("");
        }
    }

    public TreeNode<E> frontSearch(E value) {
        return root.frontSearch(value);
    }

    public void delete(E value) {
        if (root.getValue() == value) {
            root = null;
        } else {
            root.delete(value);
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

}
