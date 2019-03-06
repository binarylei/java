package com.github.binarylei.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: leigang
 * @version: 2018-05-11
 */
public class JDBCTest {

    @Test
    public void jdbcTest() throws Exception {
        //1、注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //DriverManager.registerDriver(new Driver());

        //2、获取连接
        //DriverManager 管理一组 jdbc 的操作
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

        //3、编写sql
        String  sql="select * from sys_user where id=?";

        //4、创建语句执行者
        PreparedStatement st = conn.prepareStatement(sql);

        //5、设置参数
        st.setString(1, "1");

        //6、执行sql
        ResultSet rs = st.executeQuery();

        //7、处理结果
        while(rs.next()){
            System.out.println(rs.getString("id") + "=>" + rs.getString("username"));
        }

        //8、释放资源.
        rs.close();
        st.close();
        conn.close();
    }
}
