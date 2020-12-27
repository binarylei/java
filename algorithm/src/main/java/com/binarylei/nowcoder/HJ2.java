package com.binarylei.nowcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine().toLowerCase();
        char c = bufferedReader.readLine().toLowerCase().charAt(0);

        int num = 0;
        for (int i = 0; i < line.length(); i++) {
            char c1 = line.charAt(i);
            if (c1 == c) {
                num++;
            }
        }

        System.out.println(num);
    }
}
