package com.petsimx.takoyomai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.petsimx.takoyomai.model.Menu;
import com.petsimx.takoyomai.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@PostMapping( "/create" )
	public ResponseEntity<Menu> saveMenu(@RequestBody Menu menu) {
		
		return new ResponseEntity<Menu>(menuService.saveMenu(menu), HttpStatus.CREATED);
	}
}
