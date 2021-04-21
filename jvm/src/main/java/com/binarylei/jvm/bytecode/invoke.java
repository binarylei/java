package com.binarylei.jvm.bytecode;

/**
 *
 * 有些符号引用在类加载阶段或是第一次使用时就会转换为直接引用，这种转换叫做静态解析；
 * 另外一些符号引用则是是在每次运行期转换为直接引用，这各转换叫做支柱链接。
 *
 * 1. inovkeinterface:调用接口中的方法，实际是在运行期决定的，决定到底调用实现该 接口的哪个对象的特定方法
 * 2. invokestatic: 调用静态方法。
 * 3. invokespecial: 调用自己的私有方法、构造方法(<init>)以及父类的方法
 * 4. invokevirtual: 调用虚方法，运行其动态查找的过程
 * 5. invokedynamic: 动态调用方法。
 * @author: leigang
 * @version: 2019-03-12
 */
public class invoke {
}
