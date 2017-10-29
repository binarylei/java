package com.github.binarylei.socket.nio.buffer;

import java.nio.ByteBuffer;

/**
 * capacity: 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 * limit: 界限，表示缓冲区中可以操作数据的大小。(limit后的数据不能读)
 * position: 位置，表示缓冲区中正在操作数据的位置
 * mark: 标记，表示记录当前postion的位置。可以通过reset恢复到时mark的位置
 * mark <= postion <= limit <= capacity
 */
public class BufferTest {

   public static void main(String[] args) {
       //1. 分配一个非直接缓冲区(用户地址空间，即JVM)
       ByteBuffer buf = ByteBuffer.allocate(1024);
       System.out.println(buf);//java.nio.HeapByteBuffer[pos=0 lim=1024 cap=1024] 堆空间

       //2. 向buf中写入数据
       buf.put("abcd".getBytes());
       System.out.println(buf); //[pos=4 lim=1024 cap=1024]

       //3. 切换成读模式
       buf.flip(); //limit = position; position = 0; mark = -1;
       System.out.println(buf); //[pos=0 lim=4 cap=1024]

       //4. buf.get()读数据
       for (int i = 0; i < buf.limit(); i++) {
           System.out.println((char) buf.get());
       }
       System.out.println(buf); //[pos=4 lim=4 cap=1024]

       //5. buf.rewind()再读数据
       buf.rewind(); //position = 0; mark = -1;
       System.out.println(buf); //[pos=0 lim=4 cap=1024]

       //6. buf.get(bytes)读一个字节数组
       byte[] bytes = new byte[buf.limit()];
       buf.get(bytes);
       System.out.println(new String(bytes));  //[pos=4 lim=4 cap=1024]

       //7. buf.reset()复位到上一个标记位
       buf.position(1).mark();
       System.out.println(buf);
       buf.get();
       System.out.println(buf);
       buf.reset();
       System.out.println(buf);

       //8. buf.hasRemaining()
       if (buf.hasRemaining()) {
           System.out.println(buf.remaining());
       }

       //9. buf.duplicate()
       ByteBuffer buf2 = buf.duplicate();

       //10. buf.clear() 清空缓冲区，但是缓冲区依旧存在，只是处于“遗忘”状态
       buf.clear();
   }
}
