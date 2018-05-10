package com.pojo;

import java.io.Serializable;
import java.util.List;

import com.constant.Constant;

public class Order implements Serializable {

	private static final long serialVersionUID = -1025550327297078000L;

	private int id;
	private String no;// 订单编号
	private int userId;// 下单用户 0 为后台下单
	private String userName;
	private String phone;
	private String address;
	private long createTime;// 下单时间
	private String createTimeInfo;
	private long updateTime;// 修改时间
	private String updateTimeInfo;
	private String sendTime;// 送餐时间
	private float priceOlds;// 商品原价之和
	private float priceNows;// 商品现价之和
	private float discount;// 商品折扣率
	private String taste;// 风味选择
	private String remark;// 备注选择
	private int stat;// 订单状态
	private String statInfo;
	private int flag;// 是否手动生成订单
	private String flagInfo;
	private int printNum;// 打印次数  再次打印将会出现提示框提示
	private int productCount;//订单商品个数
	private int byNums;//订单购买数
	private List<Product> productList;//订单商品
	private String record;// 修改时最后操作人以及信息

	public String getFlagInfo() {
		if(flag == Constant.FLAG_ORDER_A){
			flagInfo = "登陆用户下单";
		}else if(flag == Constant.FLAG_ORDER_B){
			flagInfo = "未登陆用户下单";
		}else{
			flagInfo = "后台手动下单";
		}
		return flagInfo;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTimeInfo() {
		return updateTimeInfo;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public int getByNums() {
		return byNums;
	}

	public void setByNums(int byNums) {
		this.byNums = byNums;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public int getPrintNum() {
		return printNum;
	}

	public void setPrintNum(int printNum) {
		this.printNum = printNum;
	}

	public String getCreateTimeInfo() {
		return createTimeInfo;
	}
	
	public String getStatInfo() {
		statInfo = Constant.STATORDERS[stat];
		return statInfo;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public float getPriceOlds() {
		return priceOlds;
	}

	public void setPriceOlds(float priceOlds) {
		this.priceOlds = priceOlds;
	}

	public float getPriceNows() {
		return priceNows;
	}

	public void setPriceNows(float priceNows) {
		this.priceNows = priceNows;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
