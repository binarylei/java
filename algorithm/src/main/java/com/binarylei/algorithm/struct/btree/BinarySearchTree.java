package com.binarylei.algorithm.struct.btree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * @author binarylei
 * @version 2020-03-08
 */
public class BinarySearchTree {

    public Object search(Node tree, int value) {
        Node p = tree;
        while (p != null) {
            if (value < p.data) {
                p = p.left;
            } else if (value > p.data) {
                p = p.right;
            } else {
                return p.data;
            }
        }
        return null;
    }

    private Node tree;

    public void add(int value) {
        if (tree == null) {
            tree = new Node(value);
            return;
        }
        Node p = tree;
        while (p != null) {
            if (value < p.data) {
                if (p.left == null) {
                    p.left = new Node(value);
                    break;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new Node(value);
                    break;
                }
                p = p.right;
            }
        }
    }

    public void remove(int data) {
        Node p = tree;  // p指向要删除的节点，初始化指向根节点
        Node pp = null; // pp记录的是p的父节点
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) p = p.right;
            else p = p.left;
        }
        // 没有找到
        if (p == null) return;

        // 要删除的节点有两个子节点
        if (p.left != null && p.right != null) { // 查找右子树中最小节点
            Node minP = p.right;
            Node minPP = p;             // minPP表示minP的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;         // 将minP的数据替换到p中
            p = minP;                   // 下面就变成了删除minP了
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        Node child;                     // p的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child;   // 删除的是根节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    @Test
    public void test() {
        add(2);
        add(1);
        System.out.println();
    }
}
