package com.binarylei.leetcode._22;//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 22. 括号生成：N格子问题。https://leetcode-cn.com/problems/generate-parentheses/
//leetcode submit region begin(Prohibit modification and deletion)
class Solution_v1 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        doGenerateParenthesis(0, 2 * n, "", result);
        return result;
    }

    /**
     * @param i      第i个格子
     * @param n      最多可以放多少个格子
     * @param result 结果
     */
    private void doGenerateParenthesis(int i, int n, String s, List<String> result) {
        if (i >= n) {
            if (validate(s)) {    // 时间复杂度 O(2^n)
                result.add(s);
            }
            return;
        }

        doGenerateParenthesis(i + 1, n, s + "(", result);
        doGenerateParenthesis(i + 1, n, s + ")", result);
    }

    private boolean validate(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) return false;
                if (stack.pop() != '(') return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        List<String> strings = new Solution_v1().generateParenthesis(3);
        System.out.println(strings);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
