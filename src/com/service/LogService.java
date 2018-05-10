package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.LogMapper;
import com.pojo.Log;
import com.pojo.PageBean;

@Service
public class LogService {

	@Autowired
	private LogMapper logMapper;

	public int getAccessCount() {
		return logMapper.getAccessCount();
	}

	public List<Log> getAccessLog(PageBean pageBean) {
		return logMapper.getAccessLog(pageBean);
	}

	public void clearAccessLog() {
		logMapper.clearAccessLog();
	}
	
}
