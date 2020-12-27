package com.binarylei.algorithm.algo.recursive._98;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author binarylei
 * @version 2020-12-27
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode of(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;
        TreeNode root = new TreeNode(arr[0], null, null);
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (++i < arr.length && node != null) {
                TreeNode left = null;
                if (arr[i] != null) {
                    left = new TreeNode(arr[i], null, null);
                }
                node.left = left;
                queue.add(left);
            }
            if (++i < arr.length && node != null) {
                TreeNode right = null;
                if (arr[i] != null) {
                    right = new TreeNode(arr[i], null, null);
                }
                node.right = right;
                queue.add(right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 4, 6, null, null, 3, 7};
        TreeNode root = of(arr);
        System.out.println(of(arr));
        System.out.println();
    }
}
