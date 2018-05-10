package com.pojo;

import java.io.Serializable;

import com.constant.Constant;

public class Tree implements Serializable {

	private static final long serialVersionUID = -1025550327297078000L;

	private int id;
	private String name;
	private String target;
	private String url;
	private int parentId;
	private int display;
	private int sort;

	public String getTarget(int target) {
		int index = 1;
		if (target >= 0 || target <= 1) {
			index = target;
		}
		return Constant.TARGETS[index];
	}

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

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
