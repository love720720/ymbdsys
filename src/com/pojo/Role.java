package com.pojo;

import java.io.Serializable;

import com.constant.Constant;

/*
 * 角色信息
 */
public class Role implements Serializable {

	private static final long serialVersionUID = -4397086644668274143L;
	
	private int id;
	private String name;// 角色名称
	private int level;// 角色等级 用于管理人员权限 通等级不可操作 不可操作高于自己等级
	private String levelInfo;
	private int sort;

	
	public String getLevelInfo() {
		levelInfo = Constant.ROLELEVELS[level];
		return levelInfo;
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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}