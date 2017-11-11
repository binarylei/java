package com.github.binarylei.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyCglibProxyUtils {

    public static UserDaoImpl getProxy (UserDaoImpl userDao) {
        Enhancer enhancer = new Enhancer();
        //1. 设置父类
        enhancer.setSuperclass(userDao.getClass());

        //2. 设置回调函数
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if ("save".equals(method.getName())) {
                    System.out.println("save ... start");
                }

                if ("update".equals(method.getName())) {
                    System.out.println("update ... start");
                }
                Object ret = methodProxy.invokeSuper(obj, args);
                return ret;
            }
        });

        //3. 获取代理对象
        UserDaoImpl userDaoImpl = (UserDaoImpl) enhancer.create();
        return userDaoImpl;
    }
}
