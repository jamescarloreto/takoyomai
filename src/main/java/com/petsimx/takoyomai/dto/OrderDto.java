package com.petsimx.takoyomai.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderDto {
	
	private List<OrderDto> orders = new ArrayList<OrderDto>();
	private long menuId;
	private int quantity;
	private String menuName;
	private double price;
	private double total;
	
	public OrderDto(long menuId, String menuName, double price, int quantity) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.price = price;
		this.quantity = quantity;
	}
	
	
}
