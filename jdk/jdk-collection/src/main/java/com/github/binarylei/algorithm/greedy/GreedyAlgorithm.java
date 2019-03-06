package com.github.binarylei.algorithm.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 贪心算法：0-1 背包问题
 *
 * @author: leigang
 * @version: 2018-12-15
 */
public class GreedyAlgorithm {

    // 候选对象
    private Bag[] candidates;
    // 背包的总承重
    private int maxWeight;

    // 解对象集合
    private Set<Bag> resultSet = new HashSet<>();
    // 解对象集合的值
    private int result;

    public GreedyAlgorithm(Bag[] candidates, int maxWeight) {
        this.candidates = candidates;
        this.maxWeight = maxWeight;
        // 对背包按单位重量价值从大到小排序
        Arrays.sort(candidates, Collections.reverseOrder());
    }

    public void select() {
        int remainingWeight = maxWeight;

        for (int i = 0; i < candidates.length; i++) {
            Bag candidate = candidates[i];
            // 判断当前物品是否可以放入背包中，若不能则继续循环，查找下一个物品
            if (check(candidate, remainingWeight)) {
                result += candidate.value;
                resultSet.add(candidate);
                remainingWeight -= candidate.weight;
            } else {
                continue;
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

    /**
     * @author: leigang
     * @version: 2018-12-15
     */
    public static class Bag implements Comparable<Bag> {

        // 物品重量
        private int weight;
        // 物品价值
        private int value;
        // 单位重量价值
        private int unitValue;

        public Bag(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.unitValue = (weight == 0) ? 0 : value / weight;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getUnitValue() {
            return unitValue;
        }

        @Override
        public int compareTo(Bag bag) {
            int value = bag.unitValue;
            if (unitValue > value)
                return 1;
            if (unitValue < value)
                return -1;
            return 0;
        }
    }
}
