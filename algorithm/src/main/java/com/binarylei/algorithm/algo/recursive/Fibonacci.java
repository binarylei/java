package com.binarylei.algorithm.algo.recursive;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author binarylei
 * @version 2020-03-03
 */
public class Fibonacci {

    // 递归，当 n 值变大时，计算非常耗时
    public int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 递归，动态规划
    public int fibonacci2(int n) {
        return fibonacci(n, new HashMap<>());
    }

    private int fibonacci(int n, Map<Integer, Integer> resolved) {
        if (resolved.containsKey(n)) {
            return resolved.get(n);
        }
        int value;
        if (n == 1 || n == 2) {
            value = 1;
        } else {
            value = fibonacci(n - 1, resolved) + fibonacci(n - 2, resolved);
        }
        resolved.put(n, value);
        return value;
    }

    // 非递归
    public int fibonacci3(int n) {
        int num1 = 1;
        int num2 = 1;
        if (n == 1 || n == 2) return 1;

        for (int i = 3; i <= n; i++) {
            int tmp = num1;
            num1 = num2;
            num2 = tmp + num2;
        }
        return num2;
    }


    @Test
    public void test() {
        for (int i = 1; i < 100; i++) {
            //Assert.assertEquals(fibonacci(i), fibonacci3(i));
            Assert.assertEquals(fibonacci2(i), fibonacci3(i));
        }
    }
}
