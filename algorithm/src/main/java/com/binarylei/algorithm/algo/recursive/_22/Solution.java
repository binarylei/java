package com.binarylei.algorithm.algo.recursive._22;

import java.util.ArrayList;
import java.util.List;

/**
 * <a herf="https://leetcode-cn.com/problems/generate-parentheses/">22. 括号生成</a>
 *
 * @author binarylei
 * @version 2020-12-29
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generateParenthesis(n, 0, 0, "", ans);
        return ans;
    }

    /**
     * @param n     输入n：表示有多少对括号
     * @param left  已经放了多少个左括号
     * @param right 已经放了多少个右括号
     * @param tmp   中间结果
     * @param ans   最终结果
     */
    private void generateParenthesis(int n, int left, int right, String tmp, List<String> ans) {
        if (right == n) {    // tmp.length == 2n
            ans.add(tmp);
            return;
        }

        if (left < n) {      // 左括号个数
            generateParenthesis(n, left + 1, right, tmp + "(", ans);
        }
        if (right < left) {  // 右括号个数
            generateParenthesis(n, left, right + 1, tmp + ")", ans);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(3));
    }
}
