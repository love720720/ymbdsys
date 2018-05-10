package com.pojo;

import java.io.Serializable;

/*
 * 角色权限信息
 */
public class RolePrivilege implements Serializable {

	private static final long serialVersionUID = 6590926226816536546L;
	
	private int id;
	private int roleId;// 角色id
	private int privilegeId;//权限id tree id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}
}