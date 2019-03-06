package com.github.binarylei.mybatis.helloworld;

import java.io.Serializable;

/**
 * @author: leigang
 * @version: 2018-08-20
 */
public class User implements Serializable {

    private int id;
    private String name;
    private int age;
    private String sex;
    private DePartment dept;

    public User() {
    }

    public User(int id, String name, int age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public User(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public DePartment getDept() {
        return dept;
    }

    public void setDept(DePartment dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
