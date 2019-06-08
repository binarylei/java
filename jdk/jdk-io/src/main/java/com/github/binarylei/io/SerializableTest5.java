package com.github.binarylei.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author: leigang
 * @version: 2018-05-05
 */
public class SerializableTest5 {

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        User user1 = new User();
        user1.setName("binarylei");
        oos.writeObject(user1);
        byte[] bytes = baos.toByteArray();
        System.out.println(bytes.length);               // length=104

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        User user2 = (User) ois.readObject();
        Assert.assertEquals(user1.getName(), user2.getName());  // 反序列化后保存了 User.name 信息
        Assert.assertNotEquals(user1, user2);                   // 反序列化后不是同一个对象

        oos.close();
        ois.close();
    }

    private static class Person implements Serializable {
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
    private static class User extends Person  {
        private String name;
        private String password;
        private transient String transientVar;
        public static String staticVar;

        public User() {
        }

        public User(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getTransientVar() {
            return transientVar;
        }

        public void setTransientVar(String transientVar) {
            this.transientVar = transientVar;
        }
    }
}


