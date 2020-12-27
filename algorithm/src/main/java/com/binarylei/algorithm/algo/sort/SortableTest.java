package com.binarylei.algorithm.algo.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author binarylei
 * @version 2020-03-04
 */
public class SortableTest {

    @Test
    public void test() {
        assertSort(new BubbleSort());
        assertSort(new SelectorSort());
        assertSort(new InsertSort());
        assertSort(new ShellSort());

        assertSort(new MergeSort());
        assertSort(new QuickSort());
        assertSort(new QuickSort_v2());

        assertSort(new HeapSort());
    }

    // 排序最基本功能测试
    private void assertSort(Sortable sortable) {
        int length = 1000;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(i);
        }
        Collections.shuffle(list);


        Integer[] arr = list.toArray(new Integer[0]);
        sortable.sort(arr);
        for (Integer i = 0; i < arr.length; i++) {
            Assert.assertEquals(i, arr[i]);
        }
    }

    public static Integer[] randomArray(int length) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        Collections.shuffle(list);

        return list.toArray(new Integer[0]);
    }

}
