package com.github.binarylei.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leigang
 * @version 2019-04-27
 * @since 2.0.0
 */
public class Slf4jTest {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Slf4jTest.class);
        logger.info("111");
    }
}
