package com.binarylei.algorithm.algo.recursive._77;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a herf="https://leetcode-cn.com/problems/combinations/">77. 组合</a>
 *
 * @author binarylei
 * @version 2020-12-29
 */
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        if (n < k) return Collections.emptyList();

        List<List<Integer>> ans = new ArrayList<>();
        combine(n, k, 1, new ArrayList<>(), ans);
        return ans;
    }

    /**
     * @param n     输入1..n
     * @param k     从 1..n 中取 k个 数
     * @param start 从哪开始取数，中间状态。下一个只能从 start + 1 开始取
     * @param path  中间结果
     * @param ans   最终结果
     */
    private void combine(int n, int k, int start, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
            path.add(i);
            combine(n, k, i + 1, path, ans);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combine(4, 2));
    }
}
