package com.github.binarylei.proxy.jdk;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();

        UserDao proxy = MyJDKProxyUtils.getProxy(userDao);
        proxy.save();
        proxy.update();
    }
}
