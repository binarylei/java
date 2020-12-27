package com.binarylei.nowcoder;

import java.util.Scanner;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        boolean[] arr = new boolean[128];
        for (int i = 0; i < line.length(); i++) {
            byte b = (byte) line.charAt(i);
            if (b < 128) {
                arr[b] = true;
            }
        }

        byte count = 0;
        for (boolean b : arr) {
            if (b) count++;
        }
        System.out.println(count);
    }
}
