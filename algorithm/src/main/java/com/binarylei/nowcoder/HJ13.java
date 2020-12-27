package com.binarylei.nowcoder;

import java.util.Scanner;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ13 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        String[] words = line.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(" ");
        }
        if (words.length != 0)
            sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        String[] words = line.split("\\s+");

        reverse(words);
        for (int i = 0; i < words.length; i++) {
            System.out.printf(words[i] + (i != words.length - 1 ? " " : ""));
        }
    }

    private static void reverse(String[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            String tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }
    }

}
