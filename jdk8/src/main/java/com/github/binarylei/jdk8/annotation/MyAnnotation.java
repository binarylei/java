package com.github.binarylei.jdk8.annotation;

import java.lang.annotation.*;

@Repeatable(MyAnnotations.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value();
}
