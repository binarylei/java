package com.github.binarylei.data.tree.bst;

import org.junit.Test;

/**
 * @author: leigang
 * @version: 2018-12-11
 */
public class BinarySortTree<T extends Comparable<T>> {

    private Node<T> root;

    public void add(Node<T> node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void midOrder() {
        root.midOrder();
    }

    public static class Node<E extends Comparable<E>> {
        private E element;
        private Node<E> left;
        private Node<E> right;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> left, Node<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        // 递归添加节点
        public void add(Node<E> node) {
            if (node.element.compareTo(this.element) < 0) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
        }

        // 递归添加节点
        public void midOrder() {
            if (this.left != null) {
                this.left.midOrder();
            }
            System.out.printf("%s ", this.element);
            if (this.right != null) {
                this.right.midOrder();
            }
        }
    }
}
