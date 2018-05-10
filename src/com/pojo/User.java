package com.pojo;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 7893491762204645828L;

	private int id;
	private String name;
	private String userName;
	private String password;
	private long lastLoginTime;
	private int loginNum;
	private String phone;
	private String address;
	private String email;
	private String lastLoginTimeInfo;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setLoginNum(int loginNum) {
		this.loginNum = loginNum;
	}

	public int getLoginNum() {
		return loginNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastLoginTimeInfo() {
		return lastLoginTimeInfo;
	}

	public void setLastLoginTimeInfo(String lastLoginTimeInfo) {
		this.lastLoginTimeInfo = lastLoginTimeInfo;
	}
}
