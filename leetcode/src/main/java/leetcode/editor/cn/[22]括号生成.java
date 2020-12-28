//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
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

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        doGenerateParenthesis(0, 0, 0, n, "", result);
        return result;
    }

    /**
     * @param i      第i个格子
     * @param n      最多可以放多少个格子
     * @param result 结果
     */
    private void doGenerateParenthesis(int i, int left, int right, int n,
            String s, List<String> result) {
        if (i >= 2 * n) {
            result.add(s);
            return;
        }

        if (left < n)       // 最多放n对括号
            doGenerateParenthesis(i + 1, left + 1, right, n, s + "(", result);
        if (right < left)   // 右括号不能超过左括号的数量
            doGenerateParenthesis(i + 1, left, right + 1, n, s + ")", result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
