package com.pojo;

import java.io.Serializable;

public class OrderStatistics implements Serializable {

	private static final long serialVersionUID = 2699444761087945040L;

	private String productNo;
	private String productName;
	private int productCount;//卖出商品个数
	private float priceNows;//卖出单个商品价格

	
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public float getPriceNows() {
		return priceNows;
	}

	public void setPriceNows(float priceNows) {
		this.priceNows = priceNows;
	}
}
