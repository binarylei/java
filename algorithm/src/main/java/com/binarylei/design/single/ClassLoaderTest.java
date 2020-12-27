package com.binarylei.design.single;

import org.junit.Assert;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author binarylei
 * @version 2020-02-29
 */
public class ClassLoaderTest {

    // 不同classLoader加载的类static区域不共享
    public static void main(String[] args) throws Exception {
        Object singleBean1 = createSingleBean();
        Object singleBean2 = createSingleBean();
        Assert.assertNotSame(singleBean1.getClass().getClassLoader(),
                singleBean2.getClass().getClassLoader());
        Assert.assertNotEquals(singleBean1.getClass().getField("randomNum").get(null),
                singleBean2.getClass().getField("randomNum").get(null));
    }

    private static Object createSingleBean() throws Exception {
        URL canonicalPath = new File(System.getProperty("user.dir"), "target/classes/")
                .toURI().toURL();
        MyClassLoader myClassLoader1 = new MyClassLoader(new URL[]{canonicalPath});
        Class<?> singleBeanClazz1 = myClassLoader1.loadClass("com.binarylei.design.single.SingleBean");
        return singleBeanClazz1.newInstance();
    }

    private static class MyClassLoader extends URLClassLoader {
        public MyClassLoader(URL[] urls) {
            super(urls);
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if ("com.binarylei.design.single.SingleBean".equals(name)) {
                return super.findClass(name);
            } else {
                return super.loadClass(name);
            }
        }
    }
}
