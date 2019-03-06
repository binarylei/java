package com.github.binarylei.jdk8.inf;

public interface MyInf2 {
	
	default String getName(){
		return "MyInf-2";
	}
	
	public static void show(){
		System.out.println("接口中的静态方法");
	}

}
