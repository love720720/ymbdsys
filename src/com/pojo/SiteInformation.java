package com.pojo;

import java.io.Serializable;

public class SiteInformation implements Serializable {

	private static final long serialVersionUID = -4768255454088334938L;

	private int id;
	private String urgencyNotice;//紧急公告
	private String notice;//公告
	private String dispatchExplain;//送餐说明
	private String timeExplain;//订餐时间
	private String phone;//电话
	private String qq;//qq
	private String address;//地址
	private String postcode;//邮编
	private String bottomExplain;//底部说明

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrgencyNotice() {
		return urgencyNotice;
	}

	public void setUrgencyNotice(String urgencyNotice) {
		this.urgencyNotice = urgencyNotice;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getDispatchExplain() {
		return dispatchExplain;
	}

	public void setDispatchExplain(String dispatchExplain) {
		this.dispatchExplain = dispatchExplain;
	}

	public String getTimeExplain() {
		return timeExplain;
	}

	public void setTimeExplain(String timeExplain) {
		this.timeExplain = timeExplain;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getBottomExplain() {
		return bottomExplain;
	}

	public void setBottomExplain(String bottomExplain) {
		this.bottomExplain = bottomExplain;
	}
}