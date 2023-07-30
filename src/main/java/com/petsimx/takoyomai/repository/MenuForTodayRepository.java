package com.petsimx.takoyomai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petsimx.takoyomai.model.MenuForToday;

public interface MenuForTodayRepository extends JpaRepository<MenuForToday, Long> {
	
	@Query( value="SELECT * FROM MENU_FOR_TODAY WHERE MENU_ID = :menuId and TODAY = CURRENT_DATE()", nativeQuery = true )
	MenuForToday findByMenuId(long menuId);
}
