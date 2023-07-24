package com.petsimx.takoyomai.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petsimx.takoyomai.dto.AjaxResponse;
import com.petsimx.takoyomai.dto.MenuDto;
import com.petsimx.takoyomai.model.Menu;
import com.petsimx.takoyomai.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@PostMapping( "/create" )
	public ResponseEntity<AjaxResponse<Object>> saveMenu(@RequestParam MultipartFile file, @RequestParam Menu menu) throws IOException {
		
		MenuDto createdDto = menuService.saveMenu(MenuDto.builder().menu(menu).file(file).build());
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", createdDto);
		return new ResponseEntity<AjaxResponse<Object>>(ajaxResponse, HttpStatus.CREATED);
	}
	
	@GetMapping( "/retrieve" )
	public ResponseEntity<AjaxResponse<Object>> saveMenu() {
		List<Menu> menus = menuService.retreiveMenu();
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", menus);
		
		return new ResponseEntity<AjaxResponse<Object>>(ajaxResponse, HttpStatus.OK);
	}
}
