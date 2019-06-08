package com.github.binarylei.log.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author leigang
 * @version 2019-04-27
 * @since 2.0.0
 */
public class ParentFirstTestSLF4J0 {

    public static void main(String[] args) throws Exception {

        URLClassLoader childClassLoader = new URLClassLoader(new URL[]{
                new URL("file:loggerprinterimpl.jar"),
                new URL("file:lib/slf4j-api-1.7.2.jar"),
                new URL("file:lib/log4j-1.2.16.jar"),
                new URL("file:lib/slf4j-log4j12-1.7.2.jar"),
        }, ParentFirstTestJCL0.class.getClassLoader());

        Logger log = LoggerFactory.getLogger(ParentFirstTestSLF4J0.class);
        log.info("message in {}", ParentFirstTestSLF4J0.class.getName());

        Class<?> cls = childClassLoader.loadClass(
                "levin.jclproblems.app.LoggerPrinterWithSLF4J");
        LoggerPrinter printer = (LoggerPrinter) cls.newInstance();
        printer.printLog();

    }
}
