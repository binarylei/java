package com.github.binarylei.helloword;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup workgroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workgroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });

            ChannelFuture f = b.connect("127.0.0.1", 8080).sync();

            //向服务器发送数据 buf
            f.channel().writeAndFlush(Unpooled.copiedBuffer("ABC".getBytes()));
            f.channel().writeAndFlush(Unpooled.copiedBuffer("DEF".getBytes()));
            f.channel().writeAndFlush(Unpooled.copiedBuffer("GHI".getBytes()));

            f.channel().closeFuture().sync();
        }  finally {
            workgroup.shutdownGracefully();
        }

    }
}
