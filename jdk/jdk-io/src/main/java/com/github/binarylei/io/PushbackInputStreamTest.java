package com.github.binarylei.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * @author leigang
 * @version 2019-06-10
 * @since 2.0.0
 */
public class PushbackInputStreamTest {

    @Test
    public void test() throws IOException {
        String s = "abcdefg";
        try (ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
             PushbackInputStream pbin = new PushbackInputStream(in)) {
            int n;
            while ((n = pbin.read()) != -1) {
                System.out.print((char) n);
                if ('b' == n) pbin.unread('U');
            }
        }
    }
}
