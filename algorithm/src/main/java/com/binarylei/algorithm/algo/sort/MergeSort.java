package com.binarylei.algorithm.algo.sort;

/**
 * 归并排序
 *
 * @author binarylei
 * @version 2020-03-04
 */
public class MergeSort implements Sortable {
    @Override
    public void sort(Integer[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * @param arr   要排序的数组
     * @param left  要排序数组的最小位置（包含）
     * @param right 要排序数组的最大位置（包含）
     */
    private void mergeSort(Integer[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);

        merge(arr, left, middle, right);
    }

    /**
     * 归并排序核心算法：合并两个有序数组，结果仍是有序。需要使用额外的数组空间，因此空间复杂度是 O(n)
     */
    private void merge(Integer[] arr, int left, int middle, int right) {
        // 为了避免频繁分配临时数组空间，可以将临时数组空间的开辟提前到sort方法中
        int[] tmpArray = new int[arr.length];

        int index = left;
        int leftIndex = left;
        int rightIndex = middle + 1;
        while (leftIndex <= middle && rightIndex <= right) {
            // 保证值相同时顺序不变
            if (arr[leftIndex] <= arr[rightIndex]) {
                tmpArray[index++] = arr[leftIndex++];
            } else {
                tmpArray[index++] = arr[rightIndex++];
            }
        }

        while (leftIndex <= middle) {
            tmpArray[index++] = arr[leftIndex++];
        }
        while (rightIndex <= right) {
            tmpArray[index++] = arr[rightIndex++];
        }

        index = left;
        while (index <= right) {
            arr[index] = tmpArray[index];
            index++;
        }
    }

}
