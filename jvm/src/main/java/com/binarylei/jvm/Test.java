package com.binarylei.jvm;

import java.io.File;
import java.io.IOException;

/**
 * @author leigang
 * @version 2019-03-07
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String path = "E:\\LG1 java\\code\\java\\jvm\\jvm-class\\src\\main\\java\\com\\gupao\\binarylei\\jvm\\gupao\\";
        System.out.println(new File(path + "-").getCanonicalPath());
        System.out.println(new File(path).getCanonicalPath());
//        System.out.println(new File(path + "*").getCanonicalPath());

    }
}
