package com.github.binarylei.data.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: leigang
 * @version: 2018-12-07
 */
public class SortTest {

    @Test
    public void bubbleSortTest() {
        int[] arr = initArr();
        Sort.bubbleSort(arr);

        assertSort(arr);
    }

    @Test
    public void insertSortTest() {
        int[] arr = initArr();
        Sort.insertSort(arr);

        assertSort(arr);
    }

    @Test
    public void chooseSortTest() {
        int[] arr = initArr();
        Sort.chooseSort(arr);

        assertSort(arr);
    }

    @Test
    public void quickSortTest() {
        int[] arr = initArr();
        Sort.quickSort(arr);

        assertSort(arr);
    }

    @Test
    public void mergeSortTest() {
        int[] arr = initArr();
        Sort.mergeSort(arr);
        assertSort(arr);
    }

    private int[] initArr() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    private void assertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            Assert.assertTrue(arr[i] <= arr[i + 1]);
        }
    }
}
