package com.github.binarylei.data.tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: leigang
 * @version: 2018-12-09
 */
public class TreeNode<E>{
    private E value;
    private TreeNode<E> left;
    private TreeNode<E> right;

    public TreeNode() {
    }

    public TreeNode(E value) {
        this.value = value;
    }

    // 二叉树遍历分为：前序遍历、中序遍历、后序遍历。即父结点的访问顺序
    public void preOrder1() {
        System.out.printf(value.toString() + " ");
        if (left != null) {
            left.preOrder1();
        }
        if (right != null) {
            right.preOrder1();
        }
    }

    // 查找
    public TreeNode<E> frontSearch(E value) {
        if (this.value == value) {
            return this;
        }
        TreeNode result = null;
        if (left != null) {
            result = left.frontSearch(value);
        }
        if (result != null) {
            return result;
        }
        if (right != null) {
            result = right.frontSearch(value);
        }
        return result;
    }

    // 删除
    public void delete(E value) {
        // 处理本结点
        TreeNode<E> parent = this;
        if (parent.left != null && parent.left.getValue() == value) {
            parent.left = null;
            return;
        }
        if (parent.right != null && parent.right.getValue() == value) {
            parent.right = null;
            return;
        }

        // 处理子结点
        parent = this.left;
        if (parent != null) {
            parent.delete(value);
        }

        parent = this.right;
        if (parent != null) {
            parent.delete(value);
        }
    }

    // =============================
    // 非递归实现前序遍历，中序遍历，后序遍历，层次遍历
    public void preOrder() {
        TreeNode<E> head = this;
        Stack<TreeNode<E>> stack = new Stack();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode<E> pop = stack.pop();
            System.out.printf(String.valueOf(pop.value) + " ");
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    public void midOrder() {
        TreeNode<E> head = this;
        Stack<TreeNode<E>> stack = new Stack();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                // 先将左结点全部入栈
                stack.push(head);
                head = head.left;
            } else {
                // 左结点全部入栈后就需要依次弹出，并处理右结点
                head = stack.pop();
                System.out.printf(String.valueOf(head.value) + " ");
                head = head.right;
            }
        }
    }

    public void postOrder() {
        TreeNode<E> head = this;
        Stack<TreeNode<E>> stack1 = new Stack();
        Stack<TreeNode<E>> stack2 = new Stack();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            TreeNode<E> tmp = stack1.pop();
            stack2.push(tmp);
            if (tmp.left != null) {
                stack1.push(tmp.left);
            }
            if (tmp.right != null) {
                stack1.push(tmp.right);
            }
        }
        while (!stack2.isEmpty()) {
            TreeNode<E> tmp = stack2.pop();
            System.out.printf(String.valueOf(tmp.value) + " ");
        }
    }

    public void levelOrder() {
        TreeNode<E> head = this;
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode<E> tmp = queue.poll();
                System.out.printf(String.valueOf(tmp.value) + " ");
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
        }
    }

    // 非递归求树的最大和最小深度
    public int maxLevel() {
        int level = 0;
        TreeNode<E> head = this;
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                level++;
                TreeNode<E> tmp = queue.poll();
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
        }
        return level;
    }

    public int minLevel() {
        int level = 0;
        TreeNode<E> head = this;
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                level++;
                TreeNode<E> tmp = queue.poll();
                if (tmp.left == null && tmp.right == null) {
                    return level;
                }
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
        }
        return 0;
    }

    // 递归求树的最大和最小深度
    public int minLevel(TreeNode head) {
        if (head == null) {
            return 0;
        }
        if (head.left == null && head.right == null) {
            return 1;
        }
        if (head.left == null && head.right != null) {
            return minLevel(head.left) + 1;
        }
        if (head.left != null && head.right == null) {
            return minLevel(head.right) + 1;
        }
        return Math.min(minLevel(head.left), minLevel(head.right)) + 1;
    }

    // 递归求两个结点的公共祖先，一个结点可以是自己的祖先
    public TreeNode ancestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == node1 || root == node2) {
            return root;
        }
        TreeNode left = ancestor(root.left, node1, node2);
        TreeNode right = ancestor(root.right, node1, node2);
        if (left == null || right == null) {
            return root;
        }
        return left != null ? left : right;
    }


    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
