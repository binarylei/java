package com.github.binarylei.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyJDKProxyUtils {

    public static UserDao getProxy (final UserDao obj) {
        UserDao proxy = (UserDao) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("save".equals(method.getName())) {
                    System.out.println("save ... start");
                }

                if ("update".equals(method.getName())) {
                    System.out.println("update ... start");
                }
                Object ret = method.invoke(obj, args);
                if ("save".equals(method.getName())) {
                    System.out.println("save ... over");
                }

                if ("update".equals(method.getName())) {
                    System.out.println("update ... over");
                }
                return ret;
            }
        });
        return proxy;
    }
}
