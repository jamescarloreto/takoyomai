package com.petsimx.takoyomai.service.impl;

import com.petsimx.takoyomai.dto.MenuDto;
import com.petsimx.takoyomai.model.FileData;
import com.petsimx.takoyomai.model.Menu;
import com.petsimx.takoyomai.repository.MenuRepository;
import com.petsimx.takoyomai.service.MenuService;
import com.petsimx.takoyomai.utils.FileDataUtils;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;

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
	public List<Menu> retreiveMenu() {
		
		return menuRepository.findAll();
	}
	
	
}
