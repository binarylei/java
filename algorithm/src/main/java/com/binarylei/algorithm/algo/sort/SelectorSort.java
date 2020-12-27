package com.binarylei.algorithm.algo.sort;

/**
 * 选择排序：时间复杂度 O(n<sup>2</sup>)。相对于冒泡排序，只对最小值交换位置。
 * 原地排序，不稳定排序。（基本不用，仅停留在理论阶段）
 *
 * @author binarylei
 * @version 2020-03-04
 */
public class SelectorSort implements Sortable {
    @Override
    public void sort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 只对最大值进行交换位置，减少交换次数
            int maxIndex = 0;
            int lastIndex = arr.length - i - 1;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[maxIndex] < arr[j]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != lastIndex) {
                int tmpValue = arr[maxIndex];
                arr[maxIndex] = arr[lastIndex];
                arr[lastIndex] = tmpValue;
            }
        }
    }

    public void sort2(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 只对最小值进行交换位置，减少交换次数
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = tmp;
            }
        }
    }

}
