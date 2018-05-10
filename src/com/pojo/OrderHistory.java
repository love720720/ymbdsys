package com.pojo;

import java.io.Serializable;

public class OrderHistory implements Serializable {

	private static final long serialVersionUID = -1025550327297078000L;

	private int id;
	private String encodeId;
	private int orderId;
	private String no;// 订单编号
	private int userId;
	private String userName;
	private long createTime;// 下单时间
	private String createTimeInfo;
	private float priceNows;// 商品现价之和
	private String remark;// 备注


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeInfo() {
		return createTimeInfo;
	}
	
	public float getPriceNows() {
		return priceNows;
	}

	public void setPriceNows(float priceNows) {
		this.priceNows = priceNows;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setCreateTimeInfo(String createTimeInfo) {
		this.createTimeInfo = createTimeInfo;
	}

	public String getEncodeId() {
		return encodeId;
	}

	public void setEncodeId(String encodeId) {
		this.encodeId = encodeId;
	}

}
