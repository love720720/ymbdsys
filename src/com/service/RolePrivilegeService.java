package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.RolePrivilegeMapper;
import com.pojo.RolePrivilege;

@Service
public class RolePrivilegeService {

	@Autowired
	private RolePrivilegeMapper rolePrivilegeMapper;
	
	public void delRolePrivilegeAsPrivilegeId(int privilegeId) {
		rolePrivilegeMapper.delRolePrivilegeAsPrivilegeId(privilegeId);
	}
	
	public void delRolePrivilegeAsRoleId(int roleId) {
		rolePrivilegeMapper.delRolePrivilegeAsRoleId(roleId);
	}

	public void insertRolePrivilege(RolePrivilege rolePrivilege) {
		rolePrivilegeMapper.insertRolePrivilege(rolePrivilege);
	}
}
