package com.herolei.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * mybatis通过@调用javal类的静态常量和方法
 */
@Component
public class Constant {

    public static int ID = 1;

    public static int ID_CONFIG;

    //spring非静态常量注入
    @Value("${config.test}")
    public String test;

    //spring静态常量注入
    @Value("${config.id}")
    public void setID_CONFIG(int ID_CONFIG) {
        this.ID_CONFIG = ID_CONFIG;
    }

    //spring静态常量直接注入 ID_CONFIG2=null
    @Value("${config.id2}")
    public static int ID_CONFIG2;

    public static int getId() {
        return 3;
    }
}
