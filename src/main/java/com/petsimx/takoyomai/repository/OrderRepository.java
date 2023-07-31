package com.petsimx.takoyomai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petsimx.takoyomai.model.Order;

import jakarta.persistence.Tuple;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT DISTINCT(mu.id), o.isPaid, mu.name, mu.price, COUNT(mu.id) FROM Order o INNER JOIN o.menus mu WHERE o.isPaid = false AND o.username = :username GROUP BY mu.id")
	List<Tuple> showOrder(String username);
	
	@Query( "SELECT o FROM Order o WHERE username = :username AND isPaid = false" )
	Order findByUsernameAndNotPaid(String username);
	
	List<Order> findByUsername(String username);
	
	@Query("SELECT DISTINCT(mu.id), o.isPaid, mu.name, mu.price, COUNT(mu.id) FROM Order o INNER JOIN o.menus mu WHERE o.orderId = :orderId GROUP BY mu.id")
	List<Tuple> showOrderByOrderId(long orderId);
	
}
