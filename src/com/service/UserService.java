package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.UserMapper;
import com.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User getLoginUser(String userName) {
		return userMapper.getLoginUser(userName);
	}

	public void updateLastLoginTime(User user) {
		userMapper.updateLastLoginTime(user);
	}

	public int insertUser(User user) {
		return userMapper.insertUser(user);
	}

	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	public int updateUserPassword(User user) {
		return userMapper.updateUserPassword(user);
	}

	public User getUser(int id) {
		return userMapper.getUser(id);
	}
}
