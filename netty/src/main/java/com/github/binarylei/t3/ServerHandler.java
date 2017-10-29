package com.github.binarylei.t3;

import com.github.binarylei.t3.data.Request;
import com.github.binarylei.t3.data.Response;
import com.github.binarylei.t3.util.GzipUtils;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ServerHandler extends ChannelHandlerAdapter {

    //@Override
    //ctx.write()后自动释放msg
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        //1. 接收 client 的请求
        Request req = (Request)msg;
        System.out.println(req);

        //1. 接收 client 发送的文件
        byte[] data = req.getAttachment();
        if (data != null) {
            try {
                byte[] ret2 = GzipUtils.ungzip(data);
                System.out.println("还原之后大小:" + ret2.length);

                String writePath = System.getProperty("user.dir") + File.separatorChar + "public" + File.separatorChar + "sunset_2.jpg";
                FileOutputStream fos = new FileOutputStream(writePath);
                fos.write(ret2);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //2. 响应 client 的请求
        Response response = new Response("" + 1, "name-" + 1, "response" + 1);
        ctx.writeAndFlush(response);//.addListener(ChannelFutureListener.CLOSE);

    }

    //@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    //@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }
}
