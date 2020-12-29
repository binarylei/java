package com.binarylei.algorithm.algo.recursive._70;

import java.util.HashMap;
import java.util.Map;

/**
 * <a herf="https://leetcode-cn.com/problems/climbing-stairs/">70. 爬楼梯</a>
 *
 * @author binarylei
 * @version 2020-12-29
 */
class Solution_3 {
    public int climbStairs(int n) {
        return climbStairs(n, new HashMap<>());
    }

    private int climbStairs(int n, Map<Integer, Integer> visited) {
        if (n == 0 || n == 1) return 1;
        if (visited.containsKey(n)) return visited.get(n);

        int value = climbStairs(n - 1, visited) + climbStairs(n - 2, visited);
        visited.put(n, value);
        return value;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_3().climbStairs(3));
    }
}
