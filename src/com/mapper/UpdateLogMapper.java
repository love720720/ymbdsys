package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.UpdateLog;

public interface UpdateLogMapper {

	public List<UpdateLog> updateLogList();

	public int insertUpdateLog(UpdateLog updateLog);

	public int getCount();

	public List<UpdateLog> loadLog(Map<String, Integer> map);
}
