package com.binarylei.nowcoder;

import java.util.Scanner;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            int length = 8;
            char[] chars = new char[length];
            int j = 0;
            for (int i = 0; i < line.length(); i++) {
                chars[j++] = line.charAt(i);
                if (j == length) {
                    System.out.println(new String(chars));
                    j = 0;
                }
            }

            if (line.length() % length != 0) {
                for (int i = j; i < 8; i++) {
                    chars[j++] = '0';
                }
                System.out.println(new String(chars));
            }
        }
    }
}
