package com.mapper;

import java.util.List;

import com.pojo.Privilege;

public interface PrivilegeMapper {

	public List<Privilege> privilegeList();

	public int insertPrivilege(Privilege privilege);

	public void updatePrivilege(Privilege privilege);

	public void deletePrivilege(int id);

	public Privilege getPrivilege(int id);

	public List<Privilege> getStaffPrivilege(int staffInfoId);

	public List<Privilege> getPrivilegeAsParentId(int privilegeId);

	public List<Privilege> getRolePrivilege(int roleId);
}
