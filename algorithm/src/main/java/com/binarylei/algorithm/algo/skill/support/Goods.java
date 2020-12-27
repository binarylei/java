package com.binarylei.algorithm.algo.skill.support;

import lombok.Data;

/**
 * 0-1 背包问题：物品的重量、价值、单元价值
 *
 * @author binarylei
 * @version 2020-03-10
 */
@Data
public class Goods implements Comparable<Goods> {

    // 物品重量
    public int weight;
    // 物品价值
    public int value;
    // 单位重量价值
    public int unitValue;

    public Goods(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.unitValue = (weight == 0) ? 0 : value / weight;
    }

    @Override
    public int compareTo(Goods o) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)", weight, value, unitValue);
    }
}
