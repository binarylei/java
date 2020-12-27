package com.binarylei.algorithm.algo.skill.branch;

import com.binarylei.algorithm.algo.skill.support.Goods;

import java.util.HashSet;
import java.util.Set;

/**
 * @author binarylei
 * @version 2020-03-10
 */
public class KnapsackBranchAndBound {

    // 候选对象
    private Goods[] candidates;
    // 背包的总承重
    private int maxWeight;

    // 解对象集合
    private Set<Goods> resultSet;
    // 解对象集合的值
    private int result;

    public KnapsackBranchAndBound(Goods[] candidates, int maxWeight) {
        this.candidates = candidates;
        this.maxWeight = maxWeight;
    }

    // 定下界
    public int minValueBound(int i) {
        int bound = 0;
        for (Goods bag : tmpSet) {
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
        for (Goods bag : tmpSet) {
            remainingWeight -= bag.weight;
        }
        return remainingWeight;
    }

    private Set<Goods> tmpSet = new HashSet<>();
    public void branch(int i) {
        if (i == candidates.length) {
            return;
        } else {
            // 放入元素
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

            // 不放入元素
            tmpSet.remove(candidates[i]);
            if (maxValueBound(i) < result) {
                System.out.println("放弃 remove " + i + " ...");
            } else {
                branch(i + 1);
            }
        }
    }

    public Set<Goods> getResultSet() {
        return resultSet;
    }

    public int getResult() {
        return result;
    }

}
