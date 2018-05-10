package com.pojo;

import java.io.Serializable;

public class StaffInfoRole implements Serializable {

	private static final long serialVersionUID = 7494294588604984030L;
	
	private int id;
	private int staffInfoId;
	private int roleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStaffInfoId() {
		return staffInfoId;
	}

	public void setStaffInfoId(int staffInfoId) {
		this.staffInfoId = staffInfoId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
