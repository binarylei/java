package com.github.binarylei.log.classloader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author leigang
 * @version 2019-04-27
 * @since 2.0.0
 */
public class LoggerPrinterWithJCL implements LoggerPrinter {

    @Override
    public void printLog() {
        Log log = LogFactory.getLog(LoggerPrinterWithJCL.class);
        log.info("Print log in class: [" + toString() + "]");
    }

    public String toString() {
        return getClass().getClassLoader().getClass().getName() +
                "->" + getClass().getName();
    }

}
