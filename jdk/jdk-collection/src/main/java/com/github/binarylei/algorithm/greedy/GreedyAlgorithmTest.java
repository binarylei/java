package com.github.binarylei.algorithm.greedy;

import org.junit.Test;

/**
 * @author: leigang
 * @version: 2018-12-15
 */
public class GreedyAlgorithmTest {

    @Test
    public void test() {
        GreedyAlgorithm.Bag[] bags = new GreedyAlgorithm.Bag[]{
                new GreedyAlgorithm.Bag(2, 13),
                new GreedyAlgorithm.Bag(1, 10),
                new GreedyAlgorithm.Bag(3, 24)};
//                new GreedyAlgorithm.Bag(2, 15),
//                new GreedyAlgorithm.Bag(4, 28),
//                new GreedyAlgorithm.Bag(5, 33),
//                new GreedyAlgorithm.Bag(3, 20),
//                new GreedyAlgorithm.Bag(1, 8)};
        int totalWeight = 3;

        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(bags, totalWeight);
        greedyAlgorithm.select();

        System.out.println(greedyAlgorithm.getResult());
    }
}
