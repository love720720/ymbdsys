package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.OrderMapper;
import com.pojo.Order;
import com.pojo.OrderStatistics;
import com.pojo.PageBean;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;

	public int insertOrder(Order order) {
		return orderMapper.insertOrder(order);
	}

	public Order getOrderToNo(String no) {
		return orderMapper.getOrderToNo(no);
	}

	public void deleteOrder(int id) {
		orderMapper.deleteOrder(id);
	}

	public int getCount() {
		return orderMapper.getCount();
	}

	public List<Order> orderList(PageBean pageBean) {
		return orderMapper.orderList(pageBean);
	}

	public Order getOrder(int id) {
		return orderMapper.getOrder(id);
	}

	public int getOrderTodayCount(long createTime) {
		return orderMapper.getOrderTodayCount(createTime);
	}

	public List<Order> orderTodayList(PageBean pageBean) {
		return orderMapper.orderTodayList(pageBean);
	}

	public void updateOrderPrintNum(int id) {
		orderMapper.updateOrderPrintNum(id);
	}

	public Order getLastOrder(long currentTime) {
		return orderMapper.getLastOrder(currentTime);
	}

	public int updateOrder(Order order) {
		return orderMapper.updateOrder(order);
	}

	public List<OrderStatistics> getOrderStatisticsDetail(Map<String, Long> map) {
		return orderMapper.getOrderStatisticsDetail(map);
	}

	public List<OrderStatistics> getProductStatistics(String ids) {
		return orderMapper.getProductStatistics(ids);
	}

	public List<Order> getSelectOrderUserName(Map<String, Object> param) {
		return orderMapper.getSelectOrderUserName(param);
	}

	public List<Order> getOrderAsUser(String name) {
		return orderMapper.getOrderAsUser(name);
	}

	public List<Order> searchOrderList(Map<String, Long> map) {
		return orderMapper.searchOrderList(map);
	}
}
