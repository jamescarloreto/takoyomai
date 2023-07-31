package com.petsimx.takoyomai.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petsimx.takoyomai.dto.OrderDto;
import com.petsimx.takoyomai.model.Menu;
import com.petsimx.takoyomai.model.Order;
import com.petsimx.takoyomai.repository.MenuRepository;
import com.petsimx.takoyomai.repository.OrderRepository;
import com.petsimx.takoyomai.service.OrderService;
import com.petsimx.takoyomai.utils.UserDetailUtils;

import jakarta.persistence.Tuple;

@Service
public class OrderServiceImpl implements OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Override
	public String placeOrder(long menuId) {
		
		String username = UserDetailUtils.getUsername();
		
		Menu menu = menuRepository.findById(menuId).get();
		List<Menu> menus = new ArrayList<Menu>();
		menus.add(menu);
		
		Order order = orderRepository.findByUsernameAndNotPaid(username);
		
		if (order == null) {
			order = Order.builder().menus(menus).orderDate(LocalDate.now()).username(username).build();
		}
		
		order.getMenus().add(menu);
		
		orderRepository.save(order);
		
		return "Order Placed";
	}

	@Override
	public OrderDto showOrder() {
		
		String username = UserDetailUtils.getUsername();
		List<Tuple> savedOrder = orderRepository.showOrder(username);
		
		OrderDto orderDto = this.setOrderDto(savedOrder);
		
		logger.info(orderDto.toString());
		
		orderDto.setTotal(this.total(orderDto.getOrders()));
		
		return orderDto;
	}

	@Override
	public String removeOrder(long menuId) {
		
		String username = UserDetailUtils.getUsername();
		Order order = orderRepository.findByUsernameAndNotPaid(username);
		
		List<Menu> menus = order.getMenus();
		List<Menu> newMenus = new ArrayList<Menu>();
	
		menus.stream().filter(menu -> !menu.equals(menuRepository.findById(menuId).get()))
		.forEach(menu -> {
			newMenus.add(menu);
		});;
		
		order.setMenus(newMenus);
		
		orderRepository.save(order);
		
		return "Remove successfully!";
	}

	@Override
	public String checkOutOrder() {
		
		String username = UserDetailUtils.getUsername();
		
		OrderDto dtoObj = this.showOrder();
		Order order = orderRepository.findByUsernameAndNotPaid(username);
		
		double total = this.total(dtoObj.getOrders());
		
		order.setTotalOrder(total);
		order.setPaidDate(LocalDate.now());
		order.setPaid(true);
		
		orderRepository.save(order);
		
		return "Checked Out Successfully";
	}
	
	private OrderDto setOrderDto(List<Tuple> orders) {
		
		OrderDto orderDto = new OrderDto();
		orders.stream().forEach(order -> {
			
			long menuId = Long.parseLong(order.get(0).toString());
			boolean isPaid = Boolean.parseBoolean(order.get(1).toString());
			String menuName = order.get(2).toString();
			double price = Double.parseDouble(order.get(3).toString());
			int qnty = Integer.parseInt(order.get(4).toString());
			
			orderDto.getOrders().add(new OrderDto(menuId, isPaid, menuName, price, qnty));
		});
		
		return orderDto;
	}
	
	private double total(List<OrderDto> orders) {
		double total = 0;
		
		for (OrderDto orderDto : orders) {
			total += (orderDto.getPrice() * orderDto.getQuantity());
		}
		
		return total;
	}

	@Override
	public List<Order> viewOrderHistory() {
		String username = UserDetailUtils.getUsername();
		
		return orderRepository.findByUsername(username);
	}

	@Override
	public OrderDto viewOrder(long orderId) {
		List<Tuple> savedOrder = orderRepository.showOrderByOrderId(orderId);
		OrderDto orderDto = this.setOrderDto(savedOrder);
		
		logger.info(orderDto.toString());
		
		orderDto.setTotal(this.total(orderDto.getOrders()));
		
		return orderDto;
	}

}
