//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        doCombine(1, new LinkedList<>(), result, n, k);
        return result;
    }

    private void doCombine(int start, LinkedList<Integer> midResult,
            List<List<Integer>> result, int n, int k) {
        if (midResult.size() == k) {
            result.add(new LinkedList<>(midResult));
            return;
        }

        for (int i = start; i <= n; i++) {
            midResult.add(i);
            doCombine(i + 1, midResult, result, n, k);
            midResult.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
