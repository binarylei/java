package com.github.binarylei.collection.sort;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Comparable this=obj返回0；this>obj返回正数；this<obj返回负数
 * @author: leigang
 * @version: 2018-05-05
 */
public class UserBean implements Comparable<UserBean> {

    public int num;

    public UserBean(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "UserBean{" + "num=" + num + '}';
    }

    @Override
    public int compareTo(UserBean user) {
        return this.num - user.num;
    }

    public static void main(String[] args) {
        ArrayList<UserBean> users = new ArrayList<>();
        users.add(new UserBean(7));
        users.add(new UserBean(3));
        users.add(new UserBean(2));
        users.add(new UserBean(4));

        users.sort(new Comparator<UserBean>() {
            @Override
            public int compare(UserBean o1, UserBean o2) {
                return o1.num - o2.num;
            }
        });

        System.out.println(users);
    }
}
