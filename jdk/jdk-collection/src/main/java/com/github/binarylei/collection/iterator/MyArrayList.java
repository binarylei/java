package com.github.binarylei.collection.iterator;

import java.util.Iterator;

/**
 * 1. 实现自己的迭代器 Iterator
 * 2. 要想使用 for (Object str : list) 则必须实现 Iterable 接口
 * @author: leigang
 * @version: 2018-05-05
 */
public class MyArrayList<T> implements Iterable<T> {

    private Object[] elements = {"java", "python", "python"};
    private int size = elements.length;

    public Iterator iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            return (T) elements[cursor++];
        }
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList();
        for (Iterator it = list.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }

        // 必须实现 Iterable 接口
        for (Object str : list) {
            System.out.println(str);
        }
    }
}
