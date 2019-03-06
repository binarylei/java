package com.github.binarylei.collection.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: leigang
 * @version: 2018-05-05
 */
public class CompareUtils {

    public static void main(String[] args) {
        int[] arr = new int[10];

        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序法：每次比较相邻的两个值，若第一个值大于第二个值就后移一位
     * 所以轮询完一次后最大值就会放到最后，下一次轮询就可以少轮询一次了。
     * 外层比较次数为“数组长度-1”次
     * 内层比较次数为“数组长度-1-已比较的次数”次
     * @param arr
     */
    public static void sort(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                count++;
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        System.out.println("比较次数：" + count);
    }

    public static void sort2(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int pos = arr.length - i -1;
            for (int j = 0; j < arr.length - i -1; j++) {
                count++;
                if (arr[pos] < arr[j]) {
                    pos = j;
                }
            }
            int tmp = arr[pos];
            arr[pos] = arr[arr.length - i -1];
            arr[arr.length - i -1] = tmp;
        }
        System.out.println("比较次数：" + count);
    }
}
