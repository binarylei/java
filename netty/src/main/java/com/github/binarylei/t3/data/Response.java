package com.github.binarylei.t3.data;

import java.io.Serializable;

public class Response implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String responseMessage;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Response(String id, String name, String responseMessage) {
		this.id = id;
		this.name = name;
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		return "Response{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", responseMessage='" + responseMessage + '\'' +
				'}';
	}
}
