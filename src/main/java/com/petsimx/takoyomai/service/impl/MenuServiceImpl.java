package com.petsimx.takoyomai.service.impl;

import com.petsimx.takoyomai.model.Menu;
import com.petsimx.takoyomai.repository.MenuRepository;
import com.petsimx.takoyomai.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;

	@Override
	public Menu saveMenu(Menu menu) {
		// TODO Auto-generated method stub
		System.out.println("service : saveMenu:");
		System.out.println(menu.toString());
		
		return menuRepository.save(menu);
	}
	
	
}
