package com.github.binarylei.bean;

public class Product {
	
	private String name;

	private Type type;
	
	public void init()
	{
		System.out.println(this);
	}
	
	public void destroy()
	{
		System.out.println("--destroy--");
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Product [name=" + name + ", type=" + type + "]";
	}
}
