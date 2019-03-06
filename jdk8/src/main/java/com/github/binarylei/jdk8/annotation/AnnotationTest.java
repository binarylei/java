package com.github.binarylei.jdk8.annotation;

import org.junit.Test;

import java.lang.reflect.Method;

public class AnnotationTest {

    @Test
    public void test() throws NoSuchMethodException {
        Class<AnnotationTest> clazz = AnnotationTest.class;
        Method method = clazz.getMethod("show", String.class);

        MyAnnotation[] ans = method.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation an : ans) {
            System.out.println(an.value());
        }
    }

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show(@MyAnnotation("param") String s) {
    }
}
