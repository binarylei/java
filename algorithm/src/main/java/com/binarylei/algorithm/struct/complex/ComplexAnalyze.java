package com.binarylei.algorithm.struct.complex;

import org.junit.Test;

/**
 * 复杂度分析
 *
 * @author binarylei
 * @version 2020-02-18
 */
public class ComplexAnalyze {

    @Test
    public void test1() {
        int sum = 0;
        int n = 1000;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j < n; j++) {
                sum += j;
            }
        }
    }

    @Test
    public void print() {
        int n = 1000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

    }


    int arr[] = new int[10];
    int len = 10;
    int i = 0;

    void add(int element) {
        if (i >= len) {
            int newArr[] = new int[len * 2];
            for (int j = 0; j < len; ++j) {
                newArr[j] = arr[j];
            }
            arr = newArr;
            len = 2 * len;
        }
        arr[i] = element;
        ++i;
    }
}
