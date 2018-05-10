package com.mapper;

import java.util.List;

import com.pojo.Log;
import com.pojo.PageBean;

public interface LogMapper {

	public int getAccessCount();

	public List<Log> getAccessLog(PageBean pageBean);

	public void clearAccessLog();

}
