package com.petsimx.takoyomai.service.impl;

import com.petsimx.takoyomai.dto.OrderDto;

public interface OrderService {

	String placeOrder(long menuId);

	OrderDto showOrder();

	String removeOrder(long menuId);

	String checkOutOrder();

}
