package com.github.binarylei.helloword;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

import java.io.UnsupportedEncodingException;

public class ServerHandler extends ChannelHandlerAdapter {

    //ctx.write()后自动释放msg
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        //读取buf中的数据
        ByteBuf buf = (ByteBuf) msg;
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);

        System.out.println(new String(data));

        //写给客户端
        ChannelFuture f = ctx.writeAndFlush(Unpooled.copiedBuffer("netty".getBytes()));
        //写完成后会自动关闭客户端
        //f.addListener(ChannelFutureListener.CLOSE);

        /*f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        });*/
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
