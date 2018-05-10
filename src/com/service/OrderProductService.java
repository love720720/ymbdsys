package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.OrderProductMapper;
import com.pojo.OrderProduct;

@Service
public class OrderProductService {

	@Autowired
	private OrderProductMapper orderProductMapper;

	public int insertOrderProduct(OrderProduct orderProduct) {
		return orderProductMapper.insertOrderProduct(orderProduct);
	}

	public void deleteOrderProductAsOrder(int orderId) {
		orderProductMapper.deleteOrderProductAsOrder(orderId);
	}
}
