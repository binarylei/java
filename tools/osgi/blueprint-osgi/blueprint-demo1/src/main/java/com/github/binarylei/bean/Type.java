package com.github.binarylei.bean;

public class Type {
	private String name;

	public Type(String name) {
		this.name = name;
	}


	public Type(String name, String password) {
		this.name = name;
	}

	public String toString() {
		return "Type [name=" + name + "]";
	}
}
