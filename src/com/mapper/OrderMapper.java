package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.Order;
import com.pojo.OrderStatistics;
import com.pojo.PageBean;

public interface OrderMapper {

	public int insertOrder(Order order);

	public Order getOrderToNo(String no);

	public void deleteOrder(int id);

	public int getCount();

	public List<Order> orderList(PageBean pageBean);

	public Order getOrder(int id);

	public int getOrderTodayCount(long createTime);

	public List<Order> orderTodayList(PageBean pageBean);

	public void updateOrderPrintNum(int id);

	public Order getLastOrder(long currentTime);

	public int updateOrder(Order order);

	public List<OrderStatistics> getOrderStatisticsDetail(Map<String, Long> map);

	public List<OrderStatistics> getProductStatistics(String ids);

	public List<Order> getSelectOrderUserName(Map<String, Object> param);

	public List<Order> getOrderAsUser(String name);

	public List<Order> searchOrderList(Map<String, Long> map);

}
