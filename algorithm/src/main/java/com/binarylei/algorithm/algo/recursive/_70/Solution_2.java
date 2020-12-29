package com.binarylei.algorithm.algo.recursive._70;

/**
 * <a herf="https://leetcode-cn.com/problems/climbing-stairs/">70. 爬楼梯</a>
 *
 * @author binarylei
 * @version 2020-12-29
 */
class Solution_2 {
    public int climbStairs(int n) {
        if (n == 0 || n == 1) return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(new Solution_2().climbStairs(3));
    }
}
