package com.github.binarylei.helloword;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        //1. 第一个线程组是用于接收Client端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2. 第二个线程组是用于处理实现的业务操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //3. ServerBootstrap 是一个启动NIO服务的辅助启动类
            ServerBootstrap b = new ServerBootstrap();
            //3.1 将两个 工作线程组加进来
            b.group(bossGroup, workerGroup)
                //3.2 指定使用NioServerSocketChannel这种类型的通道
                .channel(NioServerSocketChannel.class)
                //3.3 使用childHandler来绑定具体的事件处理器
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerHandler());
                    }
                })
                //3.4 设置TCP缓冲区大小，默认128，一般不用改
                .option(ChannelOption.SO_BACKLOG, 128)
                //3.5 设置发送缓冲区大小
                .option(ChannelOption.SO_SNDBUF, 32 * 1034)
                //3.6 设置接收缓冲区大小
                .option(ChannelOption.SO_RCVBUF, 32 * 1034)
                //3.7 KEEPALIVE
                .childOption(ChannelOption.SO_KEEPALIVE, true);

            //4. 绑定端口
            ChannelFuture f = b.bind(port).sync(); // (7)

            //5. 等待关闭通道  <=>  阻塞程序，不然Server直接执行完成后关闭，client就不可能连接上了
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new Server(port).run();
    }
}
