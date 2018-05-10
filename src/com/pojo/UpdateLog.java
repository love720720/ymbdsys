package com.pojo;

import java.io.Serializable;

import com.constant.Constant;

public class UpdateLog implements Serializable {

	private static final long serialVersionUID = -2630343557464321075L;
	
	private int id;
	private int stat;//操作状态
	private String statName;
	private long createTime;//操作更新时间
	private String createTimeInfo;
	private int staffInfoId;//操作者
	private String staffInfoName;
	private String content;//更新内容
	private boolean isNew;//是否是最近提交 限于7天内

	public boolean getIsNew() {
		long todaymillisecond = Constant.TODAYMILLISECOND;
		isNew = todaymillisecond * 7 + createTime > System.currentTimeMillis() ? true : false;
		return isNew;
	}

	public String getStatName() {
		if(stat < 0 || stat > 3){
			return null;
		}
		statName = Constant.UPDATELOGSTAT[stat];
		return statName;
	}

	public String getCreateTimeInfo() {
		return createTimeInfo;
	}

	public void setCreateTimeInfo(String createTimeInfo) {
		this.createTimeInfo = createTimeInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getStaffInfoId() {
		return staffInfoId;
	}

	public void setStaffInfoId(int staffInfoId) {
		this.staffInfoId = staffInfoId;
	}

	public String getContent() {
		return content;
	}

	public String getStaffInfoName() {
		return staffInfoName;
	}

	public void setStaffInfoName(String staffInfoName) {
		this.staffInfoName = staffInfoName;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
