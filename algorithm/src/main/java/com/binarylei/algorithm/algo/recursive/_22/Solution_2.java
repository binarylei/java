package com.binarylei.algorithm.algo.recursive._22;

import java.util.ArrayList;
import java.util.List;

/**
 * <a herf="https://leetcode-cn.com/problems/generate-parentheses/">22. 括号生成</a>
 * 扩展：2n个格子，每个格子可以放 ()，问有多少种放法
 *
 * @author binarylei
 * @version 2020-12-29
 */
class Solution_2 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generateParenthesis(n, 0, "", ans);
        return ans;
    }

    /**
     * @param n     输入n：表示有多少对括号
     * @param depth 递归深度
     * @param tmp   中间结果
     * @param ans   最终结果
     */
    private void generateParenthesis(int n, int depth, String tmp, List<String> ans) {
        if (depth == 2 * n) {    // tmp.length == 2n
            ans.add(tmp);
            return;
        }

        generateParenthesis(n, depth + 1, tmp + "(", ans);
        generateParenthesis(n, depth + 1, tmp + ")", ans);
    }

    public static void main(String[] args) {
        System.out.println(new Solution_2().generateParenthesis(3));
    }
}
