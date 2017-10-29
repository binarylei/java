package com.github.binarylei.t3.util;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 文件数据压缩后再传输
 */
public class GzipUtils {
    public GzipUtils() {
    }

    public static byte[] gzip(byte[] data) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data);
        gzip.finish();
        gzip.close();
        byte[] ret = bos.toByteArray();
        bos.close();
        return ret;
    }

    public static byte[] ungzip(byte[] data) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        GZIPInputStream gzip = new GZIPInputStream(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] buf = new byte[1024];
        int len = -1;
        while((len = gzip.read(buf, 0, buf.length)) != -1) {
            bos.write(buf, 0, len);
        }

        gzip.close();
        bis.close();
        byte[] ret = bos.toByteArray();
        bos.flush();
        bos.close();
        return ret;
    }

    public static void main(String[] args) throws Exception {
        String readPath = System.getProperty("user.dir") + File.separatorChar + "public" + File.separatorChar + "sunset.jpg";
        FileInputStream in = new FileInputStream(new File(readPath));
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();
        System.out.println("文件原始大小:" + data.length);

        byte[] ret1 = gzip(data);
        System.out.println("压缩之后大小:" + ret1.length);

        byte[] ret2 = ungzip(ret1);
        System.out.println("还原之后大小:" + ret2.length);

        String writePath = System.getProperty("user.dir") + File.separatorChar + "public" + File.separatorChar + "sunset_2.jpg";
        FileOutputStream fos = new FileOutputStream(writePath);
        fos.write(ret2);
        fos.close();
    }
}
