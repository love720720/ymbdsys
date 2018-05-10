package com.mapper;

import java.util.List;

import com.pojo.OrderHistory;

public interface OrderHistoryMapper {

	public int insertOrderHistory(OrderHistory orderHistory);

	public OrderHistory getOrderHistory(int orderId);

	public List<OrderHistory> getOrderHistoryList(int userId);

	public void updateOrderHistory(OrderHistory orderHistory);

	public void deleteOrderHistory(int orderId);
}
