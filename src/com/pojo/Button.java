package com.pojo;

import java.io.Serializable;

public class Button implements Serializable {

	private static final long serialVersionUID = -7658354048477101986L;
	
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}