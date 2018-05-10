package com.mapper;

import com.pojo.RolePrivilege;


public interface RolePrivilegeMapper {

	public void delRolePrivilegeAsPrivilegeId(int privilegeId);
	
	public void delRolePrivilegeAsRoleId(int roleId);

	public void insertRolePrivilege(RolePrivilege rolePrivilege);

}
