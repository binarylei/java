package com.github.binarylei.data.tree;

/**
 * @author: leigang
 * @version: 2018-12-09
 */
public class ArrayBinaryTree {

    int[] data;

    public ArrayBinaryTree(int[] data) {
        this.data = data;
    }

    public void preOrder() {
        preOrder(0);
    }

    // 前序遍历
    public void preOrder(int index) {
        System.out.printf(data[index] + " ");
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        // 左子树
        if (leftIndex < data.length) {
            preOrder(leftIndex);
        }
        // 右子树
        if (rightIndex < data.length) {
            preOrder(rightIndex);
        }
    }

    public void heapSort(int[] arr) {
        // 找到最大的非叶子节点
        int start = (arr.length - 1) / 2;
        for (int i = start; i >= 0; i--) {
            maxHeap(arr, arr.length, i);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;
            maxHeap(arr, i, 0);
        }
    }

    // 转换指定索引位为大顶堆
    public void maxHeap(int[] arr, int size, int index) {
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        int maxIndex = index;

        if (leftIndex < size && arr[leftIndex] > arr[maxIndex]) {
            maxIndex = leftIndex;
        }
        if (rightIndex < size && arr[rightIndex] > arr[maxIndex]) {
            maxIndex = rightIndex;
        }

        if (maxIndex != index) {
            int tmp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = tmp;
            maxHeap(arr, size, maxIndex);
        }
    }

}
