package com.binarylei.algorithm.algo.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.apache.commons.lang3.ArrayUtils.remove;
import static org.apache.commons.lang3.ArrayUtils.swap;

/**
 * i 2i+1 2i+2
 *
 * @author binarylei
 * @version 2020-03-09
 */
public class HeapSort implements Sortable {
    @Override
    public void sort(Integer[] arr) {
        heavify(arr);

        // 从大顶堆中取出最大元素，依次插入已经排序部分
        doSort(arr);
    }

    private void doSort(Integer[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            swap(arr, 0, n - i);
            shiftDown(0, n - i, arr);
        }
    }

    // 堆化
    private static void heavify(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            shiftUp(i, arr);
        }
    }

    // 大顶堆：从下往上堆化
    private static void shiftUp(int i, Integer[] arr) {
        while (i > 0 && arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    // 大顶堆：从上往下堆化
    private static void shiftDown(int i, int size, Integer[] arr) {
        while (true) {
            int maxPos = i;
            if (size > 2 * i + 1 && arr[maxPos] < arr[2 * i + 1]) {
                maxPos = 2 * i + 1;
            }
            if (size > 2 * i + 2 && arr[maxPos] < arr[2 * i + 2]) {
                maxPos = 2 * i + 2;
            }
            if (maxPos == i) {
                break;
            }
            swap(arr, i, maxPos);
            i = maxPos;
        }
    }

    @Test
    public void test() {
        Integer[] arr = SortableTest.randomArray(10);
        System.out.println(Arrays.asList(arr));

        heavify(arr);
        System.out.println(Arrays.asList(arr));

        doSort(arr);
//        arr[0] = arr[arr.length - 1];
//        shiftDown(0, arr.length - 1, arr);
        System.out.println(Arrays.asList(arr));
    }
}
