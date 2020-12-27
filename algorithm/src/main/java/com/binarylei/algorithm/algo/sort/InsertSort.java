package com.binarylei.algorithm.algo.sort;

/**
 * 插入排序：时间复杂度 O(n<sup>2</sup>)。往有序数组中插入一个元素仍是有序的。
 * 原地排序，稳定排序。（常用）
 *
 * @author binarylei
 * @version 2020-03-04
 */
public class InsertSort implements Sortable {
    @Override
    public void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1] > tmp; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }

}
