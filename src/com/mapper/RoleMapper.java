package com.mapper;

import java.util.List;

import com.pojo.PageBean;
import com.pojo.Role;

public interface RoleMapper {

	public List<Role> roleList(PageBean pageBean);

	public int insertRole(Role role);

	public void updateRole(Role role);

	public void deleteRole(int id);

	public Role getRole(int id);

	public int getCount();

	public Role getRoleAsStaffInfo(int staffInfoId);

	public List<Role> getRoleList();
}
