package com.petsimx.takoyomai.model;


import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "MENU_FOR_TODAY")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MenuForToday {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private LocalDate today;
	private long menuId;
}
