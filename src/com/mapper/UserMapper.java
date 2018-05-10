package com.mapper;

import com.pojo.User;

public interface UserMapper {

	public User getLoginUser(String userName);

	public void updateLastLoginTime(User user);

	public int insertUser(User user);

	public int updateUser(User user);

	public int updateUserPassword(User user);

	public User getUser(int id);

}
