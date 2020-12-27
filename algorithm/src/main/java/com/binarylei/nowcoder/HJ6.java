package com.binarylei.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            long value = scanner.nextLong();

            List<Long> result = process(value, new HashMap<>());
            Collections.sort(result);

            result.forEach(v -> System.out.println(v + " "));
        }
    }

    private static List<Long> process(long value, Map<Long, List<Long>> visited) {
        List<Long> list = new ArrayList<>();

        List<Long> result = div(value, visited);
        if (result.size() == 1) {
            return result;
        }

        list.addAll(process(result.get(0), visited));
        list.addAll(process(result.get(1), visited));
        return list;
    }


    private static List<Long> div(long value, Map<Long, List<Long>> visited) {
        if (visited.containsKey(value))
            return visited.get(value);
        for (long i = 2; i < value; i++) {
            if (value % i == 0) {
                return Arrays.asList(i, value / i);
            }
        }
        return Collections.singletonList(value);
    }
}
