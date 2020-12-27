package com.binarylei.design.single;

import java.net.URI;
import java.net.URL;
import java.util.Random;

/**
 * @author binarylei
 * @version 2020-02-29
 */
public class SingleBean {

    public static long randomNum = new Random().nextLong();

    static {
        System.out.println("static init, randomNum= " + randomNum);
    }
}
