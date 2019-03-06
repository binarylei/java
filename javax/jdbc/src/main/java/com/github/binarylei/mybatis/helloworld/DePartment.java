package com.github.binarylei.mybatis.helloworld;

import java.util.List;

/**
 * @author: leigang
 * @version: 2018-09-05
 */
public class DePartment {

    private int id;
    private String name;
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
