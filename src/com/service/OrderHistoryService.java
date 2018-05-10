package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.OrderHistoryMapper;
import com.pojo.OrderHistory;

@Service
public class OrderHistoryService {

	@Autowired
	private OrderHistoryMapper orderHistoryMapper;

	public int insertOrderHistory(OrderHistory orderHistory) {
		return orderHistoryMapper.insertOrderHistory(orderHistory);
	}

	public OrderHistory getOrderHistory(int orderId) {
		return orderHistoryMapper.getOrderHistory(orderId);
	}
	
	public List<OrderHistory> getOrderHistoryList(int userId) {
		return orderHistoryMapper.getOrderHistoryList(userId);
	}

	public void updateOrderHistory(OrderHistory orderHistory) {
		orderHistoryMapper.updateOrderHistory(orderHistory);
	}

	public void deleteOrderHistory(int orderId) {
		orderHistoryMapper.deleteOrderHistory(orderId);
	}
}
