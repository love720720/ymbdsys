package com.mapper;

import com.pojo.OrderProduct;

public interface OrderProductMapper {

	public int insertOrderProduct(OrderProduct orderProduct);

	public void deleteOrderProductAsOrder(int orderId);

}
