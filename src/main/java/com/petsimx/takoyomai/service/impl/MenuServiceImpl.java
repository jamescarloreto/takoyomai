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
	public MenuDto retreiveMenu(String foodType) {
		List<Menu> menus;
		MenuDto menuDto = new MenuDto();
		
		if (foodType == null) {
			menus = menuRepository.findAll();
		} else {
			logger.info("retreiveMenu | foodType :: " + foodType);
			menus = menuRepository.findByType(foodType);
		}
		
		logger.info("retreiveMenu | menus :: " + menus.toString());
		
		menus.stream()
		.forEach(menu -> {
			byte[] file = FileDataUtils.decompressImage(menu.getFile().getFileByte());
			List<MenuForToday> menusToday = menuForTodayRepository.findByMenuId(menu.getId());
			
			if (menusToday.size() != 0) {
				menusToday.stream().forEach(menuToday -> {
					if (menuToday.getToday().equals(LocalDate.now())) {
						menuDto.getMenuDtos().add(new MenuDto(menu.getId(), menu.getName(), menu.getDescription(), menu.getPrice(), menu.getType(), file, menu.getFile().getType(), true));
					} else {
						menuDto.getMenuDtos().add(new MenuDto(menu.getId(), menu.getName(), menu.getDescription(), menu.getPrice(), menu.getType(), file, menu.getFile().getType(), false));
					}
				});
			} else {
				menuDto.getMenuDtos().add(new MenuDto(menu.getId(), menu.getName(), menu.getDescription(), menu.getPrice(), menu.getType(), file, menu.getFile().getType(), false));
			}
		});
		
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
		
		List<MenuForToday> menusToday = menuForTodayRepository.findByMenuId(menuId);
		
		if (menusToday.size() != 0) {
			menusToday.stream().forEach(todayMenu -> {
				if (todayMenu.getToday().equals(LocalDate.now())) {
					menuForTodayRepository.delete(todayMenu);
				}
			});
		} else {
			return "Non to remove";
		}
		
		return "Removed successfully";
	}
	
	
}
