package com.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.constant.Constant;

public class StaffInfo implements Serializable {

	private static final long serialVersionUID = 1686434445602469481L;

	private int id;
	private String name;//姓名
	private String userName;//用户名
	private int gender;
	private String genderInfo;
	private String phone;
	private long lastLoginTime;
	private String lastLoginTimeStr;//最后登陆时间
	private String registerTimeStr;//注册时间
	private String password;
	private int stat;//账号状态
	private String loginTimeStr;//上次登录时间
	private int loginNum;//登录次数
	private int roleId;

	List<Privilege> privilegeList = new ArrayList<Privilege>();//权限
	
	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setPrivilegeList(List<Privilege> privilegeList) {
		this.privilegeList = privilegeList;
	}

	public String getGenderInfo() {
		genderInfo = Constant.GENDERS[gender];
		return genderInfo;
	}

	public String getLoginTimeStr() {
		return loginTimeStr;
	}

	public void setLoginTimeStr(String loginTimeStr) {
		this.loginTimeStr = loginTimeStr;
	}

	public int getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(int loginNum) {
		this.loginNum = loginNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegisterTimeStr() {
		return registerTimeStr;
	}

	public void setRegisterTimeStr(String registerTimeStr) {
		this.registerTimeStr = registerTimeStr;
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

	public String getLastLoginTimeStr() {
		return lastLoginTimeStr;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public void setLastLoginTimeStr(String lastLoginTimeStr) {
		this.lastLoginTimeStr = lastLoginTimeStr;
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
