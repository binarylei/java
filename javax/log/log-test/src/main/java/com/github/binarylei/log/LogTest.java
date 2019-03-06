package com.github.binarylei.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: leigang
 * @version: 2018-10-21
 */
public class LogTest {

    // jul log
    @Test
    public void JulTest() {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("jul");
        logger.info("jul 日志");
    }

    // log4j1 log
    @Test
    public void Log4j1Test() {
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(getClass());
        logger.debug("log4j1 日志");
    }

    // log4j2 log
    @Test
    public void Log4j2Test() {
        org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(getClass());
        logger.error("log4j2 日志");
    }

    // slf4j
    @Test
    public void Slf4jTest() {
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
        logger.debug("slf4j 日志");
    }

}
