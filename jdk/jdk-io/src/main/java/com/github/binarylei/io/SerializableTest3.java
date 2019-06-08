package com.github.binarylei.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: leigang
 * @version: 2018-05-05
 */
public class SerializableTest3 {

    @Test
    @SuppressWarnings("all")
    public void testWriteReplace() throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("result.obj"));
        User user = new User("binarylei", "password");
        out.writeObject(user);
        out.close();

        ObjectInputStream oin = new ObjectInputStream(
                new FileInputStream("result.obj"));
        List t = (List) oin.readObject();
        Assert.assertEquals(user.getName(), t.get(0));
        Assert.assertEquals(user.getPassword(), t.get(1));
        oin.close();
    }

    private static class User implements Serializable {
        private String name;
        private String password;

        public User() {
        }

        public User(String name, String password) {
            this.name = name;
            this.password = password;
        }

        private Object writeReplace() throws ObjectStreamException {
            List<Object> list = new ArrayList<>();
            list.add(name);
            list.add(password);
            return list;
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
    }
}


