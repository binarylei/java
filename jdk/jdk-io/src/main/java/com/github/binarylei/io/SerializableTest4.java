package com.github.binarylei.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author: leigang
 * @version: 2018-05-05
 */
public class SerializableTest4 {

    @Test
    @SuppressWarnings("all")
    public void testWriteReplace() throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("result.obj"));
        out.writeObject(Brand.NIKE);
        out.close();

        ObjectInputStream oin = new ObjectInputStream(
                new FileInputStream("result.obj"));
        Assert.assertEquals(Brand.NIKE, oin.readObject());
        oin.close();
    }

    private static class Brand implements Serializable {
        private int val;

        private Brand(int val) {
            this.val = val;
        }

        // 两个枚举值
        public static final Brand NIKE = new Brand(0);
        public static final Brand ADDIDAS = new Brand(1);

        /*private Object readResolve() throws ObjectStreamException {
            return val == 0 ? NIKE : ADDIDAS;
        }*/

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }
}


