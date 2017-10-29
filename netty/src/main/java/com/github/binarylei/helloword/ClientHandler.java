package com.github.binarylei.helloword;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter {

    //@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        try {
            //读取buf中的数据
            ByteBuf buf = (ByteBuf) msg;
            byte[] data = new byte[buf.readableBytes()];
            buf.readBytes(data);

            System.out.println(new String(data));
        } finally {
            //释放 (ByteBuf) msg
            ReferenceCountUtil.release(msg);
        }
    }

    //@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
