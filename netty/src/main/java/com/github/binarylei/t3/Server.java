package com.github.binarylei.t3;

import com.github.binarylei.t3.util.MarshallingCodeFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

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
                //3.3 设置TCP缓冲区大小，默认128，一般不用改
                .option(ChannelOption.SO_BACKLOG, 128)
                //3.4 设置发送缓冲区大小
                .option(ChannelOption.SO_SNDBUF, 32 * 1034)
                //3.5 设置接收缓冲区大小
                .option(ChannelOption.SO_RCVBUF, 32 * 1034)
                //3.6 KEEPALIVE
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //3.7 日志
                .handler(new LoggingHandler(LogLevel.INFO))
                //3.8 使用childHandler来绑定具体的事件处理器
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
                        sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
                        sc.pipeline().addLast(new ReadTimeoutHandler(5));
                        sc.pipeline().addLast(new ServerHandler());
                    }
                });

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
            port = 8765;
        }
        new Server(port).run();
    }
}
