package com.github.binarylei.mybatis.helloworld;

import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: leigang
 * @version: 2018-09-04
 */
public class MybatisTest2 {

    private static final String resource = "mybatis-config.xml";
    private static SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        Reader reader = Resources.getResourceAsReader(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void test1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            User user = sqlSession.selectOne("getUser", 1);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user1 = userMapper.getUser(1);
            System.out.println(user1);
            test5();

            User user2 = userMapper.getUser(1);
            System.out.println(user2);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test5() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.updateUser(1, "binarylei");
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            User user1 = sqlSession.selectOne("getUser", 1);
            User user2 = sqlSession.selectOne("getUser", 1);
            System.out.println("user1：" + user1);
            System.out.println("user2：" + user2);
            System.out.println(user1 == user2);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test4() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        User user1 = sqlSession1.selectOne("getUser", 1);
        sqlSession1.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        User user2 = sqlSession2.selectOne("getUser", 1);
        sqlSession2.close();
    }


    @Test
    public void test() {
        List<String> list = Arrays.asList(new String[]{"1", "2"});
        for (String str : list) {

        }
    }


}
