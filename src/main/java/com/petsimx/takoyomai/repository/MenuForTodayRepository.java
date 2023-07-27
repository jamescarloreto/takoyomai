package com.petsimx.takoyomai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petsimx.takoyomai.model.MenuForToday;

public interface MenuForTodayRepository extends JpaRepository<MenuForToday, Long> {
	
	List<MenuForToday> findByMenuId(long menuId);
}
