package com.github.binarylei.javase.generic.demo4;

/**
 * @author: leigang
 * @version: 2018-03-22
 */
public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //A<Fruit> f = new A<Apple>();
        A<Fruit> f =new A<Fruit>();
        //test(new A<Apple>());
    }
    //形参使用多态
    public static void test(A<Fruit> f){

    }
    //返回类型使用多态
    public static A<Fruit>  test2(){
        //return (A<Fruit>)(new A<Apple>());
        return null;
    }
}
