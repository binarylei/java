package com.binarylei.algorithm.algo.skill.dynamic;

/**
 * 回溯法
 * 0-1 背包问题：100kg 的背包最多可以放的最大重量 weight[]
 *
 * @author binarylei
 * @version 2020-03-10
 */
public class KnapsackBacktrack_v2 {

    private int maxWeight;
    private int[] weight;
    private int maxW = Integer.MIN_VALUE;

    public void knapsack(int i, int sw) {
        if (sw == maxWeight || i == weight.length) {
            if (sw > maxW) maxW = sw;
            return;
        }

        knapsack(i + 1, sw);
        if (sw + weight[i + 1] <= maxWeight) {
            knapsack(i + 1, sw + weight[i + 1]);
        }
    }
}
