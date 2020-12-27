package com.binarylei.algorithm.algo.skill.dynamic;

import org.junit.Test;

/**
 * 动态规划法解决 0-1 背包问题：100kg 的背包最多可以放的最大重量 weight[]
 *
 * @author binarylei
 * @version 2020-03-10
 */
public class KnapsackDynamic {

    // weight:物品重量，n:物品个数，w:背包可承载重量
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1];     // 默认值false
        states[0][0] = true;                // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) {       // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {  // 不把第i个物品放入背包
                if (states[i - 1][j] == true) states[i][j] = true;
            }
            for (int j = 0; j <= w - weight[i]; ++j) {  //把第i个物品放入背包
                if (states[i - 1][j] == true) states[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) {      // 输出结果
            if (states[n - 1][i] == true) return i;
        }
        return 0;
    }


    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j] == true) states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }

    // f(n) = max(f(n-1)) + weight[n]
    public static int knapsack3(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j] == true) states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }


    @Test
    public void test() {

    }
}
