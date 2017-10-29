package com.github.binarylei.socket.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScannerAndGatherTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("1.txt", "rw");
        FileChannel inChannel = raf.getChannel();

        //1. 获取通道
        ByteBuffer buf1 = ByteBuffer.allocate(10);
        ByteBuffer buf2 = ByteBuffer.allocate(20);

        //Gather
        ByteBuffer[] bufs = {buf1, buf2};
        inChannel.read(bufs);

        for (ByteBuffer buf : bufs) {
            buf.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        //Scanner
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel outChannel = raf2.getChannel();

        outChannel.write(bufs);

    }
}
