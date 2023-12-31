package com.petsimx.takoyomai.service;

import java.io.IOException;
import java.util.List;

import com.petsimx.takoyomai.dto.MenuDto;
import com.petsimx.takoyomai.model.Menu;

public interface MenuService {
	MenuDto saveMenu(MenuDto menuDto) throws IOException;

	MenuDto retreiveMenu(String foodType);

	String addMenuForToday(Long menuId);

	String deleteMenuForToday(Long menuId);
}
