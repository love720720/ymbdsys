package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.UpdateLogMapper;
import com.pojo.UpdateLog;

@Service
public class UpdateLogService {

	@Autowired
	private UpdateLogMapper updateLogMapper;
	
	public List<UpdateLog> updateLogList() {
		return updateLogMapper.updateLogList();
	}

	public int insertUpdateLog(UpdateLog updateLog) {
		return updateLogMapper.insertUpdateLog(updateLog);
	}

	public int getCount() {
		return updateLogMapper.getCount();
	}

	public List<UpdateLog> loadLog(Map<String, Integer> map) {
		return updateLogMapper.loadLog(map);
	}
}
