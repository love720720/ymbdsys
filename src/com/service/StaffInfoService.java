package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.StaffInfoMapper;
import com.pojo.PageBean;
import com.pojo.Staff;
import com.pojo.StaffInfo;

@Service
public class StaffInfoService {

	@Autowired
	private StaffInfoMapper staffInfoMapper;

	public StaffInfo getStaffInfo(Staff staff) {
		return staffInfoMapper.getStaffInfo(staff);
	}

	public int getCount() {
		return staffInfoMapper.getCount();
	}

	public List<StaffInfo> staffInfoList(PageBean pageBean) {
		return staffInfoMapper.staffInfoList(pageBean);
	}

	public Staff getStaff(int id) {
		return staffInfoMapper.getStaff(id);
	}

	public int updateStaff(Staff staff) {
		return staffInfoMapper.updateStaff(staff);
	}

	public int insertStaffInfo(StaffInfo staffInfo) {
		return staffInfoMapper.insertStaffInfo(staffInfo);
	}

	public int insertStaff(Staff staff) {
		return staffInfoMapper.insertStaff(staff);
	}

	public void deleteStaffInfo(int id) {
		staffInfoMapper.deleteStaffInfo(id);
	}

	public Staff getStaffAsName(String userName) {
		return staffInfoMapper.getStaffAsName(userName);
	}

	public void updateLastLoginTime(StaffInfo staffInfo) {
		staffInfoMapper.updateLastLoginTime(staffInfo);
	}

	public int updatePassword(StaffInfo staffInfo) {
		return staffInfoMapper.updatePassword(staffInfo);
	}

	public StaffInfo getStaffInfoAsId(int id) {
		return staffInfoMapper.getStaffInfoAsId(id);
	}
}
