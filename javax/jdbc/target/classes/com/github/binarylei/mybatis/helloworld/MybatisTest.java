package com.github.binarylei.mybatis.helloworld;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * @author: leigang
 * @version: 2018-09-04
 */
public class MybatisTest {

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
            System.out.println(sqlSession.selectOne("getUser", 1).hashCode());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser(1);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            User user = new User("name1", 22, "男");
            sqlSession.insert("save2", user);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test4() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User("name1", 22, "男");
            userMapper.save(user);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }

}
