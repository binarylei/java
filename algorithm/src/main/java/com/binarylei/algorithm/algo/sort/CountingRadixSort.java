package com.binarylei.algorithm.algo.sort;

/**
 * @author binarylei
 * @version 2020-03-05
 */
public class CountingRadixSort implements Sortable {
    @Override
    public void sort(Integer[] arr) {
        if (arr.length <= 1) return;

        int n = arr.length;
        // 查找数组中数据的范围
        int max = arr[0];
        for (int i = 1; i < n; ++i) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        // 申请一个计数数组c，下标大小[0,max]
        int[] c = new int[max + 1];
        for (int i = 0; i <= max; ++i) {
            c[i] = 0;
        }

        // 计算每个元素的个数，放入c中
        for (int i = 0; i < n; ++i) {
            c[arr[i]]++;
        }
        // 依次累加
        for (int i = 1; i <= max; ++i) {
            c[i] = c[i - 1] + c[i];
        }

        // 临时数组r，存储排序之后的结果
        int[] r = new int[n];

        // 计算排序的关键步骤，有点难理解
        for (int i = n - 1; i >= 0; --i) {
            int index = c[arr[i]] - 1;
            r[index] = arr[i];
            c[arr[i]]--;
        }

        // 将结果拷贝给a数组
        for (int i = 0; i < n; ++i) {
            arr[i] = r[i];
        }
    }
}
