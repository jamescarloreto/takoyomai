package com.petsimx.takoyomai.service;

import java.util.List;

import com.petsimx.takoyomai.dto.OrderDto;
import com.petsimx.takoyomai.model.Order;

public interface OrderService {

	String placeOrder(long menuId);

	OrderDto showOrder();

	String removeOrder(long menuId);

	String checkOutOrder();

	OrderDto viewOrder(long orderId);
	
	List<Order> viewOrderHistory();
}
