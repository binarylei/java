package com.binarylei.nowcoder;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        Set<Character> list = new LinkedHashSet<>();
        for (int i = line.length() - 1; i >= 0; i--) {
            list.add(line.charAt(i));
            if (list.size() == 10) {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        list.forEach(sb::append);
        System.out.println(Integer.parseInt(sb.toString()));
    }
}
