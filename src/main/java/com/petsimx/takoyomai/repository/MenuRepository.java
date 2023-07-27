package com.petsimx.takoyomai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petsimx.takoyomai.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	List<Menu> findByType(String foodType);
	
}
