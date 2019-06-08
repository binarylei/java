package com.github.binarylei.log4j;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author leigang
 * @version 2019-04-26
 * @since 2.0.0
 */
public class Log4jTest1 {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Log4jTest1.class);
        logger.info("log4j log");
    }

    @Test
    public void test() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'.xxxx'yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT")); // do all date formatting in GMT
        String r0 = simpleDateFormat.format(new Date(0));
        System.out.println(r0);
    }
}
