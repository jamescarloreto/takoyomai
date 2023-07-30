package com.petsimx.takoyomai.repository.impl;

import java.util.List;

import com.petsimx.takoyomai.dto.OrderDto;
import com.petsimx.takoyomai.repository.OrderRepository;

import jakarta.persistence.EntityManager;

public abstract class OrderRepositoryImpl {
	
	private EntityManager entityManager;
	
	public List<OrderDto> showOrder(String username) {
		return entityManager.createQuery("""
			SELECT DISTINCT(om.id) AS ids, COUNT(om.id) AS quantity 
			FROM OrderTk o INNER JOIN o.menus om 
			WHERE o.isPaid = false AND o.username = :username GROUP BY om.id""", 
			OrderDto.class)
			.setParameter(0, username) .getResultList();
	}
	
	

}
