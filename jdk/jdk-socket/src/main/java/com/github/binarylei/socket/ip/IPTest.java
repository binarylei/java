package com.github.binarylei.socket.ip;

import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author: leigang
 * @version: 2018-05-06
 */
public class IPTest {

    @Test
    public void InetAddressTest() throws UnknownHostException {
        //使用getLocalHost方法创建InetAddress对象
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostAddress());  //返回：192.168.1.100
        System.out.println(addr.getHostName());     //输出计算机名

        //根据域名得到InetAddress对象
        addr = InetAddress.getByName("www.163.com");
        System.out.println(addr.getHostAddress());  //返回 163服务器的ip:61.135.253.15
        System.out.println(addr.getHostName());     //输出：www.163.com

        //根据ip得到InetAddress对象
        addr = InetAddress.getByName("61.135.253.15");
        System.out.println(addr.getHostAddress());  //返回 163服务器的ip:61.135.253.15
        System.out.println(addr.getHostName());     //输出ip而不是域名。如果这个IP地 址不存在或DNS服务器不允许进行IP地址和域名的映射，getHostName方法就直接返回这个IP地址。

    }

    @Test
    public void InetSocketAddressTest() throws UnknownHostException {
        InetSocketAddress  address = new InetSocketAddress("127.0.0.1",9999);
        address = new InetSocketAddress(InetAddress.getByName("127.0.0.1"),9999);
        System.out.println(address.getHostName());
        System.out.println(address.getPort());

        InetAddress addr = address.getAddress();
        System.out.println(addr.getHostAddress());  //返回：地址
        System.out.println(addr.getHostName());     //输出计算机名

    }
}
