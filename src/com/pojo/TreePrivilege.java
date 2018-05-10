package com.pojo;

import java.io.Serializable;

/*
 * 页面权限
 */
public class TreePrivilege implements Serializable {
	
	private static final long serialVersionUID = 2810100470972021461L;
	
	private int id;
	private int name;// 权限名称
	private int treeId;//页面id tree id
	private int privilegeCode;//权限代码
	private int sort;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public int getTreeId() {
		return treeId;
	}

	public void setTreeId(int treeId) {
		this.treeId = treeId;
	}

	public int getPrivilegeCode() {
		return privilegeCode;
	}

	public void setPrivilegeCode(int privilegeCode) {
		this.privilegeCode = privilegeCode;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}