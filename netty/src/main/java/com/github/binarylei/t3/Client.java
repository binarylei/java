package com.github.binarylei.t3;

import com.github.binarylei.t3.data.Request;
import com.github.binarylei.t3.util.GzipUtils;
import com.github.binarylei.t3.util.MarshallingCodeFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

public class Client {

    private static class SingletonHolder {
        static final Client instance = new Client();
    }

    public static Client getInstance(){
        return SingletonHolder.instance;
    }

    private EventLoopGroup group;
    private Bootstrap b;
    private ChannelFuture f;

    private Client(){
        group = new NioEventLoopGroup();
        b = new Bootstrap();
        b.group(group)
            .channel(NioSocketChannel.class)
            .handler(new LoggingHandler(LogLevel.INFO))
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel sc) throws Exception {
                    sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
                    sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
                    //超时handler（当服务器端与客户端在指定时间以上没有任何进行通信，则会关闭响应的通道，主要为减小服务端资源占用）
                    sc.pipeline().addLast(new ReadTimeoutHandler(5));
                    sc.pipeline().addLast(new ClientHandler());
                }
            });
    }

    public void connect(){
        try {
            this.f = b.connect("127.0.0.1", 8765).sync();
            System.out.println("远程服务器已经连接, 可以进行数据交换..");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChannelFuture getChannelFuture(){

        if(this.f == null){
            this.connect();
        }
        if(!this.f.channel().isActive()){
            this.connect();
        }

        return this.f;
    }

    public static void main(String[] args) throws InterruptedException {
        final Client c = Client.getInstance();
        //c.connect();

        final ChannelFuture f = c.getChannelFuture();
        for(int i = 1; i <= 3; i++ ){
            Request request = new Request("" + i, "name-" + i, "requestMessage-" + i);
            f.channel().writeAndFlush(request);
            TimeUnit.SECONDS.sleep(4);
        }

        f.channel().closeFuture().sync();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("进入子线程...");
                    //ChannelFuture f = c.getChannelFuture();
                    System.out.println(f.channel().isActive());
                    System.out.println(f.channel().isOpen());

                    //再次发送数据，传输文件 目前文件太大无法传输
                    Request request = new Request("" + 4, "name-" + 4, "requestMessage-" + 4);
                    String readPath = System.getProperty("user.dir") + File.separatorChar + "public" + File.separatorChar + "1.png";
                    FileInputStream in = new FileInputStream(new File(readPath));
                    byte[] data = new byte[in.available()];
                    in.read(data);
                    request.setAttachment(GzipUtils.gzip(data));
                    in.close();

                    f.channel().writeAndFlush(request);
                    f.channel().closeFuture().sync();
                    System.out.println("子线程结束.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("断开连接,主线程结束..");

    }
}
