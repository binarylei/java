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
public class SerializableTest {

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

    @Test
    public void testTransient() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("user.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        User user1 = new User();
        user1.setName("binarylei");
        user1.setTransientVar("transientVar");
        oos.writeObject(user1);
        oos.close();

        FileInputStream fis = new FileInputStream("user.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user2 = (User) ois.readObject();
        Assert.assertEquals(user1.getName(), user2.getName());
        Assert.assertNull(user2.getTransientVar());

        ois.close();
    }

    @Test
    public void testStatic() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("user.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        User user1 = new User();
        user1.staticVar = "staticVar";
        oos.writeObject(user1);
        oos.close();

        FileInputStream fis = new FileInputStream("user.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user2 = (User) ois.readObject();
        System.out.println(user2.staticVar);

        ois.close();
    }

    // 同一个对象连续写两次
    @Test
    public void testSave() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        User user1 = new User();
        user1.setName("binarylei");
        oos.writeObject(user1);
        int length1 = baos.toByteArray().length;
        oos.writeObject(user1);
        int length2 = baos.toByteArray().length;
        Assert.assertEquals(5, length2 - length1);  // 同一个对象写两次，长度只增加了 5

        oos.writeUnshared(user1);
        int length3 = baos.toByteArray().length;

        System.out.println(String.format("length1=%s; length2=%s; length3=%s", length1, length2, length3));
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        User user2 = (User) ois.readObject();
        User user3 = (User) ois.readObject();
        Assert.assertEquals(user1.getName(), user2.getName());
        Assert.assertEquals(user2, user3);  // user2和user3是一个对象
        ois.close();
    }

    private static class User implements Serializable {
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


