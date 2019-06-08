package com.github.binarylei.log4j;

import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author leigang
 * @version 2019-04-26
 * @since 2.0.0
 */
public class JavaLogTest1 {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogManager().getLogger(JavaLogTest1.class.getName());
        logger.info("log4j");
    }

}
