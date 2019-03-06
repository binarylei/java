package com.github.binarylei.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 1. binarySearch(List<? extends Comparable<? super T>> list, T key)   容器有序
 * 2. sort(List<T> list)
      sort(List<T> list, Comparator<? super T> c)
 * 3. reverse(List<?> list)
 * 4. shuffle(List<?> list) 洗牌
 * 5. swap(List<?> list, int i, int j)
 * @author: leigang
 * @version: 2018-05-05
 */
public class CollectionsTest {

    @Test
    public void test() {
        List<Object> list = Arrays.asList(new String[]{"java", "python", "c++"});
        System.out.println(list);

        Collections.reverse(list);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println(list);
    }

    @Test
    public void test2() {
        List<Integer> cards = new ArrayList<>();
        List<Integer> p1 = new ArrayList<>();
        List<Integer> p2 = new ArrayList<>();
        List<Integer> p3 = new ArrayList<>();
        List<Integer> p4 = new ArrayList<>();

        for (int i = 0; i < 54; i++) {
            cards.add(i);
        }

        Collections.shuffle(cards);

        for (int i = 0; i < 51; i += 3) {
            p1.add(cards.get(i));
            p2.add(cards.get(i + 1));
            p3.add(cards.get(i + 2));
        }
        p4.add(cards.get(51));
        p4.add(cards.get(52));
        p4.add(cards.get(53));

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
    }

}
