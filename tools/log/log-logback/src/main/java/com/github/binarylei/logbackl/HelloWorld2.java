package com.github.binarylei.logbackl;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leigang
 * @version 2019-04-26
 * @since 2.0.0
 */
public class HelloWorld2 {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloWorld2.class);
        logger.debug("Hello world.");

        // print internal state
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}
