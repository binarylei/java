package com.github.binarylei.t1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

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
                        //定长拆包：5个字符，不足5位则忽略
                        sc.pipeline().addLast(new FixedLengthFrameDecoder(5));
                        //设置字符串形式的解码
                        sc.pipeline().addLast(new StringDecoder());
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });

            ChannelFuture f = b.connect("127.0.0.1", 8080).sync();

            //向服务器发送数据 buf
            f.channel().writeAndFlush(Unpooled.copiedBuffer("aaaaabbbbb".getBytes()));
            f.channel().writeAndFlush(Unpooled.copiedBuffer("cccccddd".getBytes()));

            f.channel().closeFuture().sync();
        }  finally {
            workgroup.shutdownGracefully();
        }

    }
}
