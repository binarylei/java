package com.github.binarylei.socket.url;

import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 网络爬虫的基本原理
 * @author: leigang
 * @version: 2018-05-06
 */
public class URLTest {
    
    @Test
    public void SocketTest() throws IOException {
        URL url = new URL("http://fanyi.baidu.com/#zh/en/");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                url.openStream(), "utf-8"));

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(new File("1.html"))));

        String msg = null;
        while ((msg = bufferedReader.readLine()) != null) {
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
    
    @Test
    public void test() throws MalformedURLException {
        //绝对路径构建
        URL url = new URL("http://www.baidu.com:80/index.html?uname=bjsxt");
        System.out.println("协议:" + url.getProtocol());
        System.out.println("域名:" + url.getHost());
        System.out.println("端口:" + url.getPort());
        System.out.println("资源:" + url.getFile());
        System.out.println("相对路径:" + url.getPath());
        System.out.println("锚点:" + url.getRef()); //锚点
        System.out.println("参数:" + url.getQuery());//?参数 :存在锚点  返回null ,不存在，返回正确

        url = new URL("http://www.baidu.com:80/a/");
        url = new URL(url,"b.txt"); //相对路径
        System.out.println(url.toString());
    }
}
