package com.binarylei.algorithm.struct.btree;

import com.binarylei.algorithm.algo.sort.SortableTest;
import org.junit.Test;

import static org.apache.commons.lang3.ArrayUtils.swap;

/**
 * @author binarylei
 * @version 2020-03-09
 */
public class Heap {

    private int[] arr;
    private int capcity;
    private int size;

    public Heap() {
        capcity = 1024;
        arr = new int[capcity];
    }

    // 小顶堆
    public void add(int value) {
        if (size >= capcity) return;
        arr[size] = value;
        int i = size;
        while (i > 0 && arr[i / 2] > arr[i]) {
            swap(arr, i, i / 2);
            i = i / 2;
        }
        size++;
    }

    // 小顶堆
    /*public void heavify(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            shiftUp(i, arr[i]);
        }
    }

    private void shiftUp(int i, int i1) {
    }*/

    public int poll() {
        if (size <= 0) return -1;
        int value = arr[1];
        int i = 1;
        arr[i] = arr[size];
        arr[size] = 0;
        size--;

        while (true) {
            int minPos = i;
            if (size >= 2 * i && arr[minPos] > arr[2 * i]) minPos = 2 * i;
            if (size >= 2 * i + 1 && arr[minPos] > arr[2 * i + 1]) minPos = 2 * i + 1;
            if (minPos == i) break;

            swap(arr, i, minPos);
            i = minPos;
        }
        return value;
    }


    @Test
    public void test() {
        Integer[] array = SortableTest.randomArray(100);
        for (Integer value : array) {
            add(value);
        }
//        Arrays.stream(arr).mapToObj(value -> value + ", ")
//                .forEach(System.out::print);

        int length = size;
        for (int i = 0; i < length; i++) {
            System.out.printf(poll() + ", ");
        }
    }
}
