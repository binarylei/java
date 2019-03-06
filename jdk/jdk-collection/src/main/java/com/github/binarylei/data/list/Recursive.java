package com.github.binarylei.data.list;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: leigang
 * @version: 2018-12-09
 */
public class Recursive {


    // 斐波拉契数列：1 1 2 3 5 8 13 ...
    public int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    Integer[] result = new Integer[100];

    public int fibonacci2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else if (result[n] != null) {
            return result[n];
        } else {
            result[n] = fibonacci2(n - 1) + fibonacci2(n - 2);
            return result[n];
        }
    }

    public int fibonacci3(int n) {
        if (n <= 0) {
            return n;
        }
        int[] memo = new int[n];
        memo[0] = 1;
        memo[1] = 1;
        for (int i = 2; i < n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n - 1];
    }

    /**
     * @param n    共有多少个盘子
     * @param from 第一个盘子
     * @param in   第二个盘子
     * @param to   第三个盘子
     */
    public void hanoi(int n, String from, String in, String to) {
        if (n == 1) {
            System.out.println("第 1 个从 " + from + " 移动到 " + to);
        } else {
            hanoi(n - 1, from, to, in);
            System.out.println("第 " + n + " 个从 " + from + " 移动到 " + to);
            hanoi(n - 1, in, from, to);
        }
    }

    @Test
    public void febonacciTest() {
        long startTime = System.currentTimeMillis();
        System.out.println(fibonacci3(45));
        System.out.println(System.currentTimeMillis() - startTime);
    }

    @Test
    public void hanoiTest() {
        hanoi(3, "A", "B", "C");
    }
}
