package com.binarylei.algorithm.algo.skill.backtrack;

import com.binarylei.algorithm.algo.skill.support.Goods;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 回溯法： 0-1 背包问题
 *
 * @author binarylei
 * @version 2020-03-10
 */
public class KnapsackBacktrack {

    // 候选对象
    private Goods[] candidates;
    // 背包的总承重
    private int maxWeight;

    // 解对象集合
    private boolean[] result;
    private int maxW = Integer.MIN_VALUE;   // 存储背包中物品总重量的最大值
    private boolean[] maxWResult;           // 最大重量对应的物品
    private int maxV = Integer.MIN_VALUE;   // 存储背包中物品总价值的最大值
    private boolean[] maxVResult;           // 最大价值对应的物品
    private Set<String> resoved = new HashSet<>();

    public KnapsackBacktrack() {
    }

    private KnapsackBacktrack(Goods[] candidates, int maxWeight) {
        this.candidates = candidates;
        this.maxWeight = maxWeight;
        this.result = new boolean[candidates.length];
    }

    public void backtrack(int i, int sw, int sv) {
        if (sw == maxWeight || i == candidates.length) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            print(result);
            if (sw > maxW) {
                maxW = sw;
                maxWResult = Arrays.copyOf(result, result.length);
            }
            if (sv > maxV) {
                maxV = sv;
                maxVResult = Arrays.copyOf(result, result.length);
            }
            return;
        }

        String cachedKey = String.format("%s-%s-%s", i, sw, sv);
        if (resoved.contains(cachedKey)) return;
        resoved.add(cachedKey);

        result[i] = false;                              // 不放物品
        backtrack(i + 1, sw, sv);

        if (sw + candidates[i].weight <= maxWeight) {   // 放物品，但还需要判断是否超过背包承受的重量
            result[i] = true;
            backtrack(i + 1, sw + candidates[i].weight, sv + candidates[i].value);
        }
    }

    private void print(boolean[] result) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < result.length; i++) {
            if (result[i]) {
                list.add(candidates[i].weight);
            }
        }
        System.out.println(StringUtils.join(list, ", "));
    }

    /*void backtrack(int k) {
        if (到达边界) {
            更新或者输出结果;
        } else {
            for(循环对于每一种可能进行操作 i) {
                if (限定条件合法)
                    backtrack(k + i);
            }
        }
    }*/

    @Test
    public void test() {
        Goods goods1 = new Goods(100, 100);
        Goods goods2 = new Goods(30, 90);
        Goods goods3 = new Goods(60, 120);
        Goods goods4 = new Goods(20, 80);
        Goods goods5 = new Goods(50, 75);
        Goods[] goods = new Goods[]{goods1, goods2, goods3, goods4, goods5};

        KnapsackBacktrack bagBacktrack = new KnapsackBacktrack(goods, 120);
        bagBacktrack.backtrack(0, 0, 0);
        System.out.print(String.format("最大重量：%s, 组合为：", bagBacktrack.maxW));
        bagBacktrack.print(bagBacktrack.maxWResult);

        System.out.print(String.format("最大价值：%s, 组合为：", bagBacktrack.maxV));
        bagBacktrack.print(bagBacktrack.maxVResult);
    }
}
