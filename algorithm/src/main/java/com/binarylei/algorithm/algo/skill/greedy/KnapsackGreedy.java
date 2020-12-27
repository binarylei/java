package com.binarylei.algorithm.algo.skill.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 贪心算法：0-1 背包问题
 */
public class KnapsackGreedy {

    // 候选对象
    private Bag[] candidates;
    // 背包的总承重
    private int maxWeight;

    // 解对象集合
    private Set<Bag> resultSet = new HashSet<>();
    // 解对象集合的值
    private int result;

    public KnapsackGreedy(Bag[] candidates, int maxWeight) {
        this.candidates = candidates;
        this.maxWeight = maxWeight;
        // 对背包按单位重量价值从大到小排序
        Arrays.sort(candidates, Collections.reverseOrder());
    }

    public void select() {
        int remainingWeight = maxWeight;

        for (int i = 0; i < candidates.length; i++) {
            Bag candidate = candidates[i];
            // 判断当前物品是否可以放入背包中
            if (check(candidate, remainingWeight)) {
                result += candidate.value;
                resultSet.add(candidate);
                remainingWeight -= candidate.weight;
            } else {
                break;
            }
        }
    }

    // 判定解是否己经完成
    public boolean check(Bag candidate, int remainingWeight) {
        if (remainingWeight >= candidate.weight) {
            return true;
        }
        return false;
    }

    public Set<Bag> getResultSet() {
        return resultSet;
    }

    public int getResult() {
        return result;
    }

    public static class Bag implements Comparable<Bag> {
        // 物品重量
        private int weight;
        // 物品价值
        private int value;
        // 单位重量价值
        private double unitValue;

        public Bag(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.unitValue = (weight == 0) ? 0 : value / (double) weight;
        }

        @Override
        public int compareTo(Bag bag) {
            double value = bag.unitValue;
            if (unitValue > value)
                return 1;
            if (unitValue < value)
                return -1;
            return 0;
        }
    }
}
