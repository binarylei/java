package com.github.binarylei.mybatis.helloworld;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: leigang
 * @version: 2018-08-20
 */
public interface UserMapper {

    void save(User user);

    void save2(User user);

    List<User> getUsers();

    User getUser(int id);

    int updateUser(@Param("id") int id, @Param("name") String name);

    User getUser2(@Param("id") int id, String name);

    User getUser3(User user);

    User getUser4(Map<String, Object> map);

    List<User> getUsers2();

    @MapKey("id")
    Map<Integer, User> getUsers3();

    List<User> getUsers5();

    List<User> getUsers6();

    List<User> getUsers7();

    List<DePartment> getDept8();

    List<DePartment> getDept9();

    List<User> getUsers10(@Param("ids") List<Integer> ids);

    List<User> getUsers11(User user);
}
