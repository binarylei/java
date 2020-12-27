package com.binarylei.algorithm.algo.bsearch;

import org.junit.Test;

import java.util.List;

/**
 * @author binarylei
 * @version 2020-03-06
 */
public class BinarySearch {

    public int search(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (value < arr[mid]) {
                high = mid - 1;
            } else if (value == arr[mid]) {
                return mid;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    @Test
    public void test() {

        int length = 1000;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = i;
        }

        int index = search(arr, 5);
        System.out.println(index);
    }

    private static <T> int indexedBinarySearch(List<? extends Comparable<? super T>> list, T key) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = list.get(mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found
    }


    // 查找第一个值等于给定值的元素
    public int bsearch1(int[] arr, int value) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (low < n && arr[low] == value) return low;
        else return -1;
    }

    // 查找第一个值等于给定值的元素
    public int bsearch2(int[] arr, int value) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (arr[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }


    public int bsearch3(int[] arr, int value) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == n - 1) || (arr[mid + 1] != value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }


    public int bsearch4(int[] arr, int value) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= value) {
                if ((mid == 0) || (arr[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    public int bsearch7(int[] arr, int value) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || (arr[mid + 1] > value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }
}
