package com.binarylei.nowcoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ3 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int count = scanner.nextInt();
            List<Integer> tmpList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                tmpList.add(scanner.nextInt());
            }
            TreeSet<Integer> treeSet = new TreeSet<>(tmpList);
            treeSet.forEach(System.out::println);
        }
    }
}
