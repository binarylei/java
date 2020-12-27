package com.binarylei.algorithm.algo.sort;

/**
 * 希尔排序：时间复杂度 O(n<sup>2</sup>)。往有序数组中插入一个元素仍是有序的。
 * 原地排序，稳定排序。（常用）
 *
 * @author binarylei
 * @version 2020-03-04
 */
public class ShellSort implements Sortable {
    @Override
    public void sort(Integer[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > tmp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
    }

}
