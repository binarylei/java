package com.github.binarylei.jdk8.inf;

public class SubClass /*extends MyClass*/ implements MyInf1, MyInf2 {

	@Override
	public String getName() {
		return MyInf2.super.getName();
	}
}
