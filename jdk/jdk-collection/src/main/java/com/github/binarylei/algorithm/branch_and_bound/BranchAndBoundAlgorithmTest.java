package com.github.binarylei.algorithm.branch_and_bound;

import com.github.binarylei.algorithm.branch_and_bound.BranchAndBoundAlgorithm.Bag;
import org.junit.Test;

import java.util.Set;

/**
 * 分支定界算法：0-1 背包问题
 *
 * @author: leigang
 * @version: 2018-12-15
 */
public class BranchAndBoundAlgorithmTest {

    @Test
    public void test() {
        Bag[] bags = new Bag[]{
                new Bag(2, 13),
                new Bag(1, 10),
                new Bag(3, 24)};
        int totalWeight = 3;

        BranchAndBoundAlgorithm algorithm = new BranchAndBoundAlgorithm(bags, totalWeight);
        algorithm.branch(0);

        Set<Bag> resultSet = algorithm.getResultSet();
        for (Bag bag : resultSet) {
            System.out.println(bag);
        }

        System.out.println(algorithm.getResult());
    }
}
