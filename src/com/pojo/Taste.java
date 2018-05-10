package com.pojo;

import java.io.Serializable;

public class Taste implements Serializable {

	private static final long serialVersionUID = -1025550327297078000L;

	private int id;
	private String name;
	private int sort;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
