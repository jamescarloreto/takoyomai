package com.petsimx.takoyomai.service.impl;

import com.petsimx.takoyomai.dto.MenuDto;
import com.petsimx.takoyomai.model.FileData;
import com.petsimx.takoyomai.model.Menu;
import com.petsimx.takoyomai.model.MenuForToday;
import com.petsimx.takoyomai.repository.MenuForTodayRepository;
import com.petsimx.takoyomai.repository.MenuRepository;
import com.petsimx.takoyomai.service.MenuService;
import com.petsimx.takoyomai.utils.FileDataUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private MenuForTodayRepository menuForTodayRepository;

	@Override
	public MenuDto saveMenu(MenuDto menuDto) throws IOException {
		
		Menu menu = menuDto.getMenu();
		
		FileData fileData = FileData.builder()
				.name(menuDto.getFile().getOriginalFilename())
				.type(menuDto.getFile().getContentType())
				.fileByte(FileDataUtils.compressImage(menuDto.getFile().getBytes()))
				.build();
		
		menu.setFile(fileData);
		Menu retrievedMenu = menuRepository.save(menu);
		
		return MenuDto.builder().menu(retrievedMenu).build();
	}

	@Override
	public synchronized MenuDto retreiveMenu(String foodType) {
		List<Menu> menus;
		MenuDto menuDto = new MenuDto();
		List<MenuDto> menuDtos = new ArrayList<MenuDto>();
		
		if (foodType == null) {
			menus = menuRepository.findAll();
		} else {
			logger.info("retreiveMenu | foodType :: " + foodType);
			menus = menuRepository.findByType(foodType);
		}
		
		menus.stream()
		.forEach(menu -> {
			byte[] file = FileDataUtils.decompressImage(menu.getFile().getFileByte());
			MenuForToday menuToday = menuForTodayRepository.findByMenuId(menu.getId());
			
			if (menuToday != null) {
				menuDto.getMenuDtos().add(new MenuDto(menu.getId(), menu.getName(), menu.getDescription(), menu.getPrice(), menu.getType(), file, menu.getFile().getType(), true));
			} else {
				menuDto.getMenuDtos().add(new MenuDto(menu.getId(), menu.getName(), menu.getDescription(), menu.getPrice(), menu.getType(), file, menu.getFile().getType(), false));
			}
		});
		
		if (foodType != null) {
			menuDto.getMenuDtos().stream().filter(dto -> dto.isToday())
			.forEach(menu -> {
				menuDtos.add(menu);
			});
			
			menuDto.setMenuDtos(menuDtos);
		}
		
		
		logger.info("retreiveMenu | menuDto :: " + menuDto.getMenuDtos().toString());
		return menuDto;
	}

	@Override
	public String addMenuForToday(Long menuId) {
		MenuForToday menuToday = MenuForToday.builder().menuId(menuId).today(LocalDate.now()).build();
		
		menuForTodayRepository.save(menuToday);
		
		return "Added successfully";
	}

	@Override
	public String deleteMenuForToday(Long menuId) {
		
		MenuForToday menuToday = menuForTodayRepository.findByMenuId(menuId);
		
		if (menuToday != null) {
			menuForTodayRepository.delete(menuToday);
		} else {
			return "Non to remove";
		}
		
		return "Removed successfully";
	}
	
	
}
