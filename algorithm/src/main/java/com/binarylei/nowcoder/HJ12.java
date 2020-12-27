package com.binarylei.nowcoder;

import java.util.Scanner;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ12 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();

        StringBuilder sb = new StringBuilder();
        for (int i = value.length() - 1; i >= 0; i--) {
            sb.append(value.charAt(i));
        }
        System.out.println(sb.toString());
    }

    public static void main(String value) {
        char[] chars = new char[value.length()];
        int j = 0;
        for (int i = value.length() - 1; i >= 0; i--) {
            chars[j++] = value.charAt(i);
        }
        System.out.println(new String(chars));
    }
}
