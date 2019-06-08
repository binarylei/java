package com.github.binarylei.log.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author leigang
 * @version 2019-04-27
 * @since 2.0.0
 */
public class ParentFirstTestJCL1 {

    public static void main(String[] args) throws Exception {

        URLClassLoader childClassLoader = new URLClassLoader(new URL[] {
                new URL("file:loggerprinterimpl.jar"),
                new URL("file:lib/commons-logging-1.0.4.jar"),
                new URL("file:lib/log4j-1.2.16.jar")
        }, ParentFirstTestJCL1.class.getClassLoader());

        Thread.currentThread().setContextClassLoader(childClassLoader);
        Class<?> cls = childClassLoader.loadClass(
                "com.github.binarylei.log.classloader.LoggerPrinterWithJCL");

        LoggerPrinter printer = (LoggerPrinter) cls.newInstance();
        printer.printLog();

    }
}
