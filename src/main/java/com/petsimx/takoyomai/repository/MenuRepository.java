package com.petsimx.takoyomai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petsimx.takoyomai.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	
}
