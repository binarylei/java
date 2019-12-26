package com.github.binarylei.concurrent.barrier;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author leigang
 * @version 2019-05-11
 * @since 2.0.0
 */
public class LoadStoreTest {

    public static void main(String[] args) throws Exception {
        Set<LoadStoreTest> result = new HashSet<>();

        int count = 0;
        while (true) {
            LoadStoreTest reordering = new LoadStoreTest();
            Thread thread1 = new Thread(reordering::thread1);
            Thread thread2 = new Thread(reordering::thread2);

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            count++;
            if (result.add(reordering)) {
                System.out.println(count + " " + reordering);
            }
            if (count > 100000) {
                break;
            }
        }
    }

    private int x = 0, y = 0;
    private int a = 0, b = 0;

    public void thread1() {
        x = b;  // 1
        a = 1;  // 2
    }

    public void thread2() {
        y = a;  // 3
        b = 1;  // 4
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoadStoreTest that = (LoadStoreTest) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }
}
