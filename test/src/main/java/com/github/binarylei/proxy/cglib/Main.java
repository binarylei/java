package com.github.binarylei.proxy.cglib;

public class Main {

    public static void main(String[] args) {
        UserDaoImpl proxy = MyCglibProxyUtils.getProxy(new UserDaoImpl());
        proxy.save();
        proxy.update();
    }
}
