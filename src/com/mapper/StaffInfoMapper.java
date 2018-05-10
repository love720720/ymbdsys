package com.mapper;

import java.util.List;

import com.pojo.PageBean;
import com.pojo.Staff;
import com.pojo.StaffInfo;

public interface StaffInfoMapper {

	public StaffInfo getStaffInfo(Staff staff);

	public int getCount();

	public List<StaffInfo> staffInfoList(PageBean pageBean);

	public Staff getStaff(int id);

	public int updateStaff(Staff staff);

	public int insertStaffInfo(StaffInfo staffInfo);

	public int insertStaff(Staff staff);

	public void deleteStaffInfo(int id);

	public Staff getStaffAsName(String userName);

	public void updateLastLoginTime(StaffInfo staffInfo);

	public int updatePassword(StaffInfo staffInfo);

	public StaffInfo getStaffInfoAsId(int id);
}
