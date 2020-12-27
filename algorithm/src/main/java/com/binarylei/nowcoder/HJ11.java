package com.binarylei.nowcoder;

import java.util.Scanner;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ11 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();

        int num = 0;
        while (value / 10 != 0) {
            num = num * 10 + value % 10;
            value = value / 10;
        }
        num = num * 10 + value % 10;

        System.out.println(num);
    }


}
