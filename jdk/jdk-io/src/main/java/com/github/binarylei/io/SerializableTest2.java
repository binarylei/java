package com.github.binarylei.io;

import org.junit.Assert;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author: leigang
 * @version: 2018-05-05
 */
public class SerializableTest2 {

    @Test
    @SuppressWarnings("all")
    public void testEncryptionPassword() throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("result.obj"));
        out.writeObject(new User("binarylei", "password"));
        out.close();

        ObjectInputStream oin = new ObjectInputStream(
                new FileInputStream("result.obj"));
        User t = (User) oin.readObject();
        Assert.assertEquals("password", t.getPassword());
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

        private void writeObject(ObjectOutputStream out) throws Exception {
            ObjectOutputStream.PutField putFields = out.putFields();
            putFields.put("password", password + "-1");
            out.writeFields();
        }

        private void readObject(ObjectInputStream in) throws Exception {
            ObjectInputStream.GetField readFields = in.readFields();
            String encryptionPassword = (String) readFields.get("password", "");
            // 模拟解密,需要获得本地的密钥
            password = encryptionPassword.substring(0, encryptionPassword.indexOf('-'));
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


