package com.binarylei.nowcoder;

import java.io.IOException;

/**
 * @author binarylei
 * @version 2020-11-05
 */
public class HJ1 {

    public static void main(String[] args) throws IOException {

        int length = 0;
        char value;
        while ((value = (char) System.in.read()) != '\n') {
            if (value != ' ') {
                length++;
            } else {
                length = 0;
            }
        }


        System.out.println(length);
    }
}
