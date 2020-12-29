package com.binarylei.algorithm.algo.recursive._70;

/**
 * <a herf="https://leetcode-cn.com/problems/climbing-stairs/">70. 爬楼梯</a>
 *
 * @author binarylei
 * @version 2020-12-29
 */
class Solution {
    public int climbStairs(int n) {
        if (n == 0 || n == 1) return 1;

        int f1 = 1, f2 = 1, f3 = f1 + f2;
        for (int i = 2; i <= n; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().climbStairs(3));
    }
}
