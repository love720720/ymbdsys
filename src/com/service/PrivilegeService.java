package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.PrivilegeMapper;
import com.pojo.Privilege;

@Service
public class PrivilegeService {

	@Autowired
	private PrivilegeMapper privilegeMapper;
	
	public List<Privilege> privilegeList() {
		return privilegeMapper.privilegeList();
	}

	public int insertPrivilege(Privilege privilege) {
		return privilegeMapper.insertPrivilege(privilege);
	}

	public void updatePrivilege(Privilege privilege) {
		privilegeMapper.updatePrivilege(privilege);
	}
	
	public void deletePrivilege(int id) {
		privilegeMapper.deletePrivilege(id);
	}

	public Privilege getPrivilege(int id) {
		return privilegeMapper.getPrivilege(id);
	}

	public List<Privilege> getStaffPrivilege(int staffInfoId) {
		return privilegeMapper.getStaffPrivilege(staffInfoId);
	}

	public List<Privilege> getPrivilegeAsParentId(int privilegeId) {
		return privilegeMapper.getPrivilegeAsParentId(privilegeId);
	}

	public List<Privilege> getRolePrivilege(int roleId) {
		return privilegeMapper.getRolePrivilege(roleId);
	}
}