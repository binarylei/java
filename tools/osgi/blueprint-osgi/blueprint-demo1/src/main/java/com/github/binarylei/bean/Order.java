package com.github.binarylei.bean;

import java.util.List;
import java.util.Map;

public class Order {
	private String id;
	private List<Product> products;
	private Map map ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void init()
	{
		System.out.println("===============");
		System.out.println(this);
	}
	public String toString() {
		return "Order [id=" + id + ", map=" + map + ", products=" + products + "]";
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
}
