package com.github.binarylei.data.tree.huffman;

import com.github.binarylei.data.tree.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @author: leigang
 * @version: 2018-12-11
 */
public class HuffmanTree {

    public static TreeNode<Integer> huffman(int[] arr) {
        List<TreeNode<Integer>> huffmanTree = new ArrayList<>();
        for (int i : arr) {
            huffmanTree.add(new TreeNode(i));
        }
        while (huffmanTree.size() > 1) {
            // 1. 排序
            Collections.sort(huffmanTree, (TreeNode<Integer> o1, TreeNode<Integer> o2) -> {
                return o1.getValue() - o2.getValue();
            });
            // 2. 取出最小的 2 个数形成一个新的节点
            TreeNode<Integer> left = huffmanTree.remove(0);
            TreeNode<Integer> right = huffmanTree.remove(0);
            TreeNode<Integer> parent = new TreeNode(left.getValue() + right.getValue());
            parent.setLeft(left);
            parent.setRight(right);
            huffmanTree.add(parent);
        }
        return huffmanTree.get(0);
    }

    @Test
    public void test() {
        String str = "can you can a can as a can canner can a can.";
        System.out.println(str.length());
        for (int i = 0; i < str.length(); i++) {
//            System.out.printf("%s ", (int)str.charAt(i));
//            System.out.printf("%s ", Integer.toBinaryString(str.charAt(i)));
            System.out.printf("%s ", map.get(str.charAt(i)));
        }
    }

    private static final Map<Character, String> map = new HashMap<>();

    static {
        map.put('a', "10");
        map.put(' ', "01");
        map.put('n', "00");
        map.put('c', "111");
        map.put('o', "11000");
        map.put('.', "11001");
        map.put('y', "110010");
        map.put('e', "110101");
        map.put('u', "110100");
        map.put('s', "110111");
        map.put('r', "110110");
    }

}
