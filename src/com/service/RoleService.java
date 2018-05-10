package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.RoleMapper;
import com.pojo.PageBean;
import com.pojo.Role;

@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	public List<Role> roleList(PageBean pageBean) {
		return roleMapper.roleList(pageBean);
	}

	public int insertRole(Role role) {
		return roleMapper.insertRole(role);
	}

	public void updateRole(Role role) {
		roleMapper.updateRole(role);
	}
	
	public void deleteRole(int id) {
		roleMapper.deleteRole(id);
	}

	public Role getRole(int id) {
		return roleMapper.getRole(id);
	}

	public int getCount() {
		return roleMapper.getCount();
	}

	public Role getRoleAsStaffInfo(int staffInfoId) {
		return roleMapper.getRoleAsStaffInfo(staffInfoId);
	}

	public List<Role> getRoleList() {
		return roleMapper.getRoleList();
	}
}
