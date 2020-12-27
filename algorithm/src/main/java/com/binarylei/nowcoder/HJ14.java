package com.binarylei.nowcoder;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ14 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String lengthStr = scanner.nextLine();
        String[] arr = new String[Integer.parseInt(lengthStr)];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextLine();
        }

        sort(arr);
        for (String s : arr) {
            System.out.println(s);
        }
    }

    private static void sort(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (compare(arr[i], arr[j]) > 0) {
                    swap(arr, i, j);
                }
            }
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // a>b 正数
    private static int compare(String a, String b) {
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) != b.charAt(i))
                return a.charAt(i) - b.charAt(i);
        }

        return a.length() - b.length();
    }

    @Test
    public void test() {
        System.out.println(compare("abc", "abc"));
        System.out.println(compare("abcd", "abc"));
        System.out.println(compare("aac", "abc"));
        System.out.println(compare("abc", "aac"));
    }

}
