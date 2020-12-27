package com.binarylei.nowcoder;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        int length = Integer.parseInt(line);
        long[] longs = new long[length];

        Map<Integer, Long> treeMap = new TreeMap<>();
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            String[] arr = line.split(" ");
            int index = Integer.parseInt(arr[0]);
            Long value = Long.parseLong(arr[1]);

            Long sum = treeMap.get(index);
            treeMap.put(index, sum == null ? value : sum + value);
        }

        treeMap.forEach((index, sum) -> System.out.println(
                index + " " + sum));
    }
}
