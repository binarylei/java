package com.github.binarylei.t1;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {

    //ctx.write()后自动释放msg
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        System.out.println((String)msg);

        //写给客户端
        ChannelFuture f = ctx.writeAndFlush(Unpooled.copiedBuffer(((String)msg).getBytes()));
        //写完成后会自动关闭客户端
        //f.addListener(ChannelFutureListener.CLOSE);

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
