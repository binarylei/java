package com.github.binarylei.logbackl;

import ch.qos.logback.classic.BasicConfigurator;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

/**
 * @author leigang
 * @version 2019-04-25
 * @since 2.0.0
 */
public class Main {

    public static void main(String[] args) {
        LoggerContext context = new LoggerContext();
        Logger logger = context.getLogger(Main.class);

        BasicConfigurator basicConfigurator = new BasicConfigurator();
        basicConfigurator.configure(context);
        logger.info("xxx");
    }
}
