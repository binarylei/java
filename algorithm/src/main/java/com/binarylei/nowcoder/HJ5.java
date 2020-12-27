package com.binarylei.nowcoder;

import java.util.Scanner;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String hexNum = scanner.nextLine();

            long num = Long.parseLong(hexNum.substring(2), 16);
            System.out.println(num);
        }
    }
}
