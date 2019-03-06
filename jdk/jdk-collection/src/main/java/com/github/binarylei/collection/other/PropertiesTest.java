package com.github.binarylei.collection.other;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * HashTable 线程安全，HashMap 线程不安全
 * Properties 是 HashTable 的子类，键与值都是 String
 *
 * 1. 设置值 setProperty(String key, String value)
 * 2.
 * 3. 存储为 .properties 文件
 *    store(Writer writer, String comments)
 *    store(OutputStream out, String comments)
 * @author: leigang
 * @version: 2018-05-05
 */
public class PropertiesTest {

    @Test
    public void test() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("name", "binarylei");

        properties.store(new FileOutputStream(new File("c:/1.txt")), "cc");
        properties.storeToXML(new FileOutputStream(new File("c:/2.txt")), "cc");
    }
}
