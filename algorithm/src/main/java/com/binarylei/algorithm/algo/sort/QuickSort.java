package com.binarylei.algorithm.algo.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author binarylei
 * @version 2020-03-04
 */
public class QuickSort implements Sortable {
    @Override
    public void sort(Integer[] arr) {
        if (arr.length <= 1) return;

        List<Integer> samller = new ArrayList<>();
        List<Integer> same = new ArrayList<>();
        List<Integer> larger = new ArrayList<>();
        Integer chooseItem = arr[(arr.length - 1) / 2];
        for (Integer item : arr) {
            if (item < chooseItem) {
                samller.add(item);
            } else if (item.intValue() == chooseItem.intValue()) {
                same.add(item);
            } else {
                larger.add(item);
            }
        }

        Integer[] samllerArr = samller.toArray(new Integer[0]);
        Integer[] largerArr = larger.toArray(new Integer[0]);
        sort(samllerArr);
        sort(largerArr);

        for (int i = 0; i < samllerArr.length; i++) {
            arr[i] = samllerArr[i];
        }
        for (int i = 0; i < same.size(); i++) {
            arr[samllerArr.length + i] = same.get(i);
        }
        for (int i = 0; i < largerArr.length; i++) {
            arr[samllerArr.length + same.size() + i] = largerArr[i];
        }
    }

    public void sort(List<Integer> list) {
        if (list.size() <= 1) return;

        List<Integer> samller = new ArrayList<>();
        List<Integer> same = new ArrayList<>();
        List<Integer> larger = new ArrayList<>();
        Integer chooseItem = list.get((list.size() - 1) / 2);
        for (Integer item : list) {
            if (item < chooseItem) {
                samller.add(item);
            } else if (item.intValue() == chooseItem.intValue()) {
                same.add(item);
            } else {
                larger.add(item);
            }
        }

        sort(samller);
        sort(larger);

        list.clear();
        list.addAll(samller);
        list.addAll(same);
        list.addAll(larger);
    }

}
