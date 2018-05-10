package com.pojo;

import java.io.Serializable;

public class OrderProduct implements Serializable {

	private static final long serialVersionUID = -1025550327297078000L;

	private int id;
	private int orderId;
	private int productId;
	private int byNum;
	private float priceNow;
	private float priceNows;
	private float priceOld;
	private float priceOlds;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getByNum() {
		return byNum;
	}

	public void setByNum(int byNum) {
		this.byNum = byNum;
	}

	public float getPriceNow() {
		return priceNow;
	}

	public void setPriceNow(float priceNow) {
		this.priceNow = priceNow;
	}

	public float getPriceNows() {
		return priceNows;
	}

	public void setPriceNows(float priceNows) {
		this.priceNows = priceNows;
	}

	public float getPriceOld() {
		return priceOld;
	}

	public void setPriceOld(float priceOld) {
		this.priceOld = priceOld;
	}

	public float getPriceOlds() {
		return priceOlds;
	}

	public void setPriceOlds(float priceOlds) {
		this.priceOlds = priceOlds;
	}
}
