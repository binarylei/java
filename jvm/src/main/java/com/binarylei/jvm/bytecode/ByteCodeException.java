package com.binarylei.jvm.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * 1. 构造方法：对于 Java 类中的每一个实例方法(非 static 方法)，其在编译后所生成的字节码中，
 * 方法参数的数量总是会比源代码中方法的数量多一个(this)，它位于方法的第一个参数位置，这样，
 * 我们可以在 Java 的实例方法中使用 this 来去访问当前对象的属性以及其方法。
 * <p>
 * 这个操作是在编译期间完成的，即由 Javac 编译器在编译的时候对 this 的访问转化为一个普通实例方法参数的访问；
 * 接下来在运行期由 JVM 在调用实例方法时，自动向实例方法传入该 this 参数，所以，在实例方法的局部变量表中，
 * 至少会有一个指向当前对象的局部变量。
 * <p>
 * Java 字节码对于异常的处理方式：
 * 1. 统一采用异常表的方式对异常进行处理。
 * 2. 在 JDK 1.4.2 之前的版本中，采用特定的指令方式。
 * 3. 当异常处理存在 finally 语句块时，JVM 采取的处理方式是将 finally 语句块的字节码拼接到每一个 catch 块后面
 *
 * @author: leigang
 * @version: 2019-03-09
 */
public class ByteCodeException {

    public void test() {
        try {
            FileInputStream in = new FileInputStream("text.txt");
            ServerSocket serverSocket = new ServerSocket(9999);
            serverSocket.accept();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception e) {
        } finally {
            System.out.println("finally!");
        }
    }

}
