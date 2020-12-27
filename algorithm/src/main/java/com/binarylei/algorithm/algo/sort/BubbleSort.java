package com.binarylei.algorithm.algo.sort;

/**
 * 冒泡排序：时间复杂度 O(n<sup>2</sup>)。依次将前后两个数据进行比较
 * 原地排序，稳定排序。（基本不用，仅停留在理论阶段）
 *
 * @author binarylei
 * @version 2020-03-04
 */
public class BubbleSort implements Sortable {
    @Override
    public void sort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 每次轮询依次比较相邻的两个数，将最小的移到前面
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public void sort2(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean swap = false;
            // 每次轮询依次比较相邻的两个数，将最小的移到前面
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap = true;
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            if (!swap) return;
        }
    }

}
