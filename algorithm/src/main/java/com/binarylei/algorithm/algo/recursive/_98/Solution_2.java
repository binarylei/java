package com.binarylei.algorithm.algo.recursive._98;

import org.junit.Test;

/**
 * <a herf="https://leetcode-cn.com/problems/validate-binary-search-tree/">98. 验证二叉搜索树</a>
 *
 * @author binarylei
 * @version 2020-12-27
 */
public class Solution_2 {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * @param root 中间结点
     * @param min  子结点(右子树或左子树)的最小值
     * @param max  子结点(右子树或左子树)的最大值
     */
    public boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;

        if (min != null && root.val <= min) return false;        //
        if (max != null && root.val >= max) return false;

        if (!isValidBST(root.left, min, root.val)) return false; // 左子树有最大值
        if (!isValidBST(root.right, root.val, max)) return false;// 右子树有最小值
        return true;
    }

    @Test
    public void test() {
        TreeNode root = TreeNode.of(new Integer[]{5, 4, 6, null, null, 3, 7});

        boolean b = isValidBST(root);
        System.out.println(b);
    }
}
