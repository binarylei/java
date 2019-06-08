package com.github.binarylei.internal;

import com.github.binarylei.util.MyTest;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

public class Activator implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        MyTest test = new MyTest();
        test.sayHello();

        ApplicationContext springContext = new ClassPathXmlApplicationContext("spring-context.xml");
        MyTest test1 = (MyTest) springContext.getBean("test");
        test.sayHello();

        System.out.println(Activator.class.getClassLoader().getResource("/"));
    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }

    public static void main(String[] args) {
        ApplicationContext springContext = new ClassPathXmlApplicationContext(
                "spring-context.xml");
        MyTest test = (MyTest) springContext.getBean("test");
        test.sayHello();
    }
}
