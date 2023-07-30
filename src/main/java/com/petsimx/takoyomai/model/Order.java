package com.petsimx.takoyomai.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table (name = "ORDER")
public class Order {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long orderId;
	
	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "order_menu", 
			joinColumns = @JoinColumn(
				name = "orderId",
				referencedColumnName = "orderId"),
			inverseJoinColumns = @JoinColumn (
				name = "menuId",
				referencedColumnName = "id"))
	private List<Menu> menus;
	
	private String username;
	private LocalDate orderDate;
	private boolean isPaid;
	private double totalOrder;
	private LocalDate paidDate;
}
