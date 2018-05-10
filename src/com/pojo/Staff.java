package com.pojo;

import java.io.Serializable;

public class Staff implements Serializable {

	private static final long serialVersionUID = 1686434445602469481L;

	private int id;
	private int staffInfoId;
	private String userName;
	private String password;
	private long registerTime;
	private int stat;

	public int getStaffInfoId() {
		return staffInfoId;
	}

	public void setStaffInfoId(int staffInfoId) {
		this.staffInfoId = staffInfoId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}
}