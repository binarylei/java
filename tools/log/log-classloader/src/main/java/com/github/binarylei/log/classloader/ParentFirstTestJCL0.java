package com.github.binarylei.log.classloader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author leigang
 * @version 2019-04-27
 * @since 2.0.0
 */
public class ParentFirstTestJCL0 {

    public static void main(String[] args) throws Exception {
        URLClassLoader childClassLoader = new URLClassLoader(new URL[] {
                new URL("file:loggerprinterimpl.jar"),
                new URL("file:lib/commons-logging-1.1.1.jar"),
                new URL("file:lib/log4j-1.2.16.jar")
        }, ParentFirstTestJCL0.class.getClassLoader());

        //Thread.currentThread().setContextClassLoader(childClassLoader);
        Log log = LogFactory.getLog(ParentFirstTestJCL0.class);
        log.info("message in " + ParentFirstTestJCL0.class.getName());

        Class<?> cls = childClassLoader.loadClass(
                "com.github.binarylei.log.classloader.LoggerPrinterWithJCL");
        LoggerPrinter printer = (LoggerPrinter)cls.newInstance();
        printer.printLog();

    }
}
