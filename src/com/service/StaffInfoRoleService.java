package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.StaffInfoRoleMapper;
import com.pojo.StaffInfoRole;

@Service
public class StaffInfoRoleService {

	@Autowired
	private StaffInfoRoleMapper staffInfoRoleMapper;
	
	public int insertStaffInfoRole(StaffInfoRole staffInfoRole) {
		return staffInfoRoleMapper.insertStaffInfoRole(staffInfoRole);
	}

	public void deleteStaffInfoRoleAsStaffId(int staffInfoId) {
		staffInfoRoleMapper.deleteStaffInfoRoleAsStaffId(staffInfoId);
	}
}
