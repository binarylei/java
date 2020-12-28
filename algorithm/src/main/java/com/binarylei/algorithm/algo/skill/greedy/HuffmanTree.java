package com.binarylei.algorithm.algo.skill.greedy;

import com.binarylei.algorithm.support.Node;
import org.junit.Test;

import java.util.Arrays;

/**
 * 赫夫曼编码：所有的编码不能以其它编码开关。如果 a=01 那么 b=011 就不允许，因为 b 的编码是以 a 开关的。
 * trie 树要求只有叶子结点存储数据，这样当所有的字符都在叶子结点时，就不会有编码重复
 *
 * @author binarylei
 * @version 2020-03-10
 */
public class HuffmanTree {

    public static TreeNode huffman(Integer[] arr) {
        if (arr.length == 0) return null;

        Arrays.sort(arr);
        TreeNode head = new TreeNode(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            TreeNode left, right;
            if (head.item <= arr[i]) {
                left = head;
                right = new TreeNode(arr[i]);
            } else {
                left = new TreeNode(arr[i]);
                right = head;
            }
            head = new TreeNode(left, right);
        }
        return head;
    }

    private static class TreeNode extends Node<Integer> {
        private TreeNode(int value) {
            this.item = value;
        }

        private TreeNode(TreeNode left, TreeNode right) {
            this.left = left;
            this.right = right;
            this.item = left.item + right.item;
        }
    }

    @Test
    public void test() {
        Integer[] arr = {1, 5, 6, 7, 2};
        Arrays.sort(arr);
        TreeNode huffman = huffman(arr);
        System.out.println(huffman.midToString());
        System.out.println(huffman.leftToString());
        System.out.println(huffman.rightToString());
        System.out.println(huffman.levelToString());
        System.out.println(huffman.maxLevel());
        System.out.println(huffman.minLevel());
    }

}
