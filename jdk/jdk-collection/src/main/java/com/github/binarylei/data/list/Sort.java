package com.github.binarylei.data.list;

/**
 * @author: leigang
 * @version: 2018-12-07
 */
public class Sort {

    /**
     * 1. 冒泡排序：前后两个数字比较
     * 需要比较 n - 1 轮，每轮需要比较 n - 1 - i 次
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 每次轮询依次比较相邻的两个数，将最大的移到后面
            /*for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }*/
            // 每次轮询依次比较相邻的两个数，将最小的移到前面
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }

    // 2. 插入排序：认为前面的所有数据而且是有序的
    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 每次轮询都将 i + 1 的数字正确排序
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }

    // 希尔排序
    public static void shellSort(int[] arr) {
        // 步长 d
        for (int d = arr.length / 2; d >= 1; d /= 2) {
            // 0 ~ d-1 组的第一个元素作为有序列，从 d 开始插入
            for (int i = d; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - d]) {
                    // 只要不小于前一个数，就一直向前
                    while (j - d >= 0 && temp < arr[j - d]) {
                        arr[j] = arr[j - d];
                        j -= d;
                    }
                    // 插入
                    arr[j] = temp;
                }
            }
        }
    }

    // 3. 选择排序：每次将最小的数字移到前面
    public static void chooseSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 每次轮询都将最小的数字移到前面
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

    // 4. 归并排序
    public static void mergeSort(int[] arr) {
        mergeSort0(arr, 0, arr.length - 1);
    }

    public static void mergeSort0(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort0(arr, start, mid);
        mergeSort0(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int index = 0;
        int i1 = start;
        int i2 = mid + 1;
        // 1. 依次比较两个元素的大小
        while (i1 <= mid && i2 <= end) {
            int value;
            if (arr[i1] < arr[i2]) {
                value = arr[i1++];
            } else {
                value = arr[i2++];
            }
            tmp[index++] = value;
        }
        // 2. 剩余数据处理
        while (i1 <= mid) {
            tmp[index++] = arr[i1++];
        }
        while (i2 <= end) {
            tmp[index++] = arr[i2++];
        }

        // 3. 将排序好的数据重新放回数组中
        index = 0;
        for (int i = start; i <= end; i++) {
            arr[i] = tmp[index++];
        }
    }

    // 将两个有序数组合并
    public static int[] merge(int[] arr1, int[] arr2) {
        int[] tmp = new int[arr1.length + arr2.length];
        int index = 0;
        int i1 = 0;
        int i2 = 0;
        while (i1 < arr1.length && i2 < arr2.length) {
            int value;
            if (arr1[i1] < arr2[i2]) {
                value = arr1[i1];
                i1++;
            } else {
                value = arr2[i2];
                i2++;
            }
            tmp[index++] = value;
        }
        for (; i1 < arr1.length; i1++) {
            tmp[index++] = arr1[i1];
        }
        for (; i2 < arr2.length; i2++) {
            tmp[index++] = arr2[i2];
        }
        return tmp;
    }

    public static void quickSort(int[] arr) {
        quickSort0(arr, 0, arr.length - 1);
    }

    private static void quickSort0(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int standard = arr[start];
        // 记录排序的下标
        int low = start;
        int hight = end;

        while (low < hight) {
            // 1. 从后向前直到找到比 standard 小的数
            while (low < hight && standard <= arr[hight]) {
                hight--;
            }
            // 比 standard 小的数丢到小端
            arr[low] = arr[hight];

            // 2. 从前向后直到找到比 standard 大的数
            while (low < hight && standard >= arr[low]) {
                low++;
            }
            // 比 standard 大的数丢到大端
            arr[hight] = arr[low];
        }
        arr[low] = standard;
        quickSort0(arr, start, low);
        quickSort0(arr, low + 1, end);
    }
}
