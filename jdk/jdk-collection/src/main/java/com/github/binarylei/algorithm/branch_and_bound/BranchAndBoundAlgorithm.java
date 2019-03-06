package com.github.binarylei.algorithm.branch_and_bound;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 分支定界算法：0-1 背包问题
 *
 * @author: leigang
 * @version: 2018-12-15
 */
public class BranchAndBoundAlgorithm {

    // 候选对象
    private Bag[] candidates;
    // 背包的总承重
    private int maxWeight;
    private Set<Bag> tmpSet = new HashSet<>();

    // 解对象集合
    private Set<Bag> resultSet;
    // 解对象集合的值
    private int result;

    public BranchAndBoundAlgorithm(Bag[] candidates, int maxWeight) {
        this.candidates = candidates;
        this.maxWeight = maxWeight;
        // 对背包按单位重量价值从大到小排序
//        Arrays.sort(candidates, Collections.reverseOrder());
    }

    // 定下界
    public int minValueBound(int i) {
        int bound = 0;
        for (Bag bag : tmpSet) {
            bound += bag.value;
        }
        return bound;
    }

    // 定上界
    public int maxValueBound(int i) {
        int maxUnitValue = 0;
        for (int j = i + 1; j < candidates.length; j++) {
            if (candidates[j].unitValue > maxUnitValue) {
                maxUnitValue = candidates[j].unitValue;
            }
        }
        return minValueBound(i) + maxUnitValue * remainingWeight(i);
    }

    // 返回还剩下的重量
    public int remainingWeight(int i) {
        int remainingWeight = maxWeight;
        for (Bag bag : tmpSet) {
            remainingWeight -= bag.weight;
        }
        return remainingWeight;
    }

    public void branch(int i) {
        if (i == candidates.length) {
            return;
        } else {

            tmpSet.add(candidates[i]);
            if (remainingWeight(i) >= 0) {
                if (maxValueBound(i) < result) {
                    System.out.println("放弃 add  " + i + "...");
                } else {
                    if (minValueBound(i) > result) {
                        result = minValueBound(i);
                        resultSet = new HashSet<>(tmpSet);
                    }
                    branch(i + 1);
                }
            }

            tmpSet.remove(candidates[i]);
            if (maxValueBound(i) < result) {
                System.out.println("放弃 remove " + i + " ...");
            } else {
                branch(i + 1);
            }
        }
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

        @Override
        public String toString() {
            return "Bag{" +
                    "weight=" + weight +
                    ", value=" + value +
                    ", unitValue=" + unitValue +
                    '}';
        }
    }
}
