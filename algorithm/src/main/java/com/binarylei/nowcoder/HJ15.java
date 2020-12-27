package com.binarylei.nowcoder;

import java.util.Scanner;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ15 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        int count = 0;
        while (num != 0) {
            if (num % 2 != 0)
                count++;
            num /= 2;
        }

        System.out.println(count);
    }

}
