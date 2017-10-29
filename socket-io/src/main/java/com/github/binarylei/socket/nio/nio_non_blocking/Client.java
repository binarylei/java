package com.github.binarylei.socket.nio.nio_non_blocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

//客户端
public class Client {

	public static void main(String[] args) {

		SocketChannel sChannel = null;
		ByteBuffer buf = null;

		try {
			//1. 获取通道
			sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8765));

			//2. 切换成非阻塞模式
			sChannel.configureBlocking(false);

			//3. 分配缓冲区
			buf = ByteBuffer.allocate(1024);

			//4. 把从控制台读到数据发送给服务器端
			while(true){
				//定义一个字节数组，然后使用系统录入功能：
				byte[] bytes = new byte[1024];
				System.in.read(bytes);

				//把从控制台读到数据发送给服务器端
				buf.put(bytes).flip();
				sChannel.write(buf);
				buf.clear();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(sChannel != null){
				try {
					sChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
