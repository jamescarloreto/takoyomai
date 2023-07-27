package com.petsimx.takoyomai.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuService;
	
	@PostMapping( value = "/create", consumes = "multipart/form-data")
	public ResponseEntity<AjaxResponse<Object>> saveMenu(@RequestParam MultipartFile file, @RequestParam String name, 
			@RequestParam String description, @RequestParam double price, @RequestParam String type) throws IOException {
		
		Menu menu = Menu.builder().name(name).description(description).price(price).type(type).build();
		
		MenuDto createdDto = menuService.saveMenu(MenuDto.builder().menu(menu).file(file).build());
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", createdDto);
		return new ResponseEntity<AjaxResponse<Object>>(ajaxResponse, HttpStatus.CREATED);
	}
	
	@GetMapping( "/retrieve" )
	public ResponseEntity<Object> retrieveMenu() {
		logger.info("MenuController | retrieve - retrieveMenu | START");
		MenuDto menuDto = menuService.retreiveMenu(null);
		logger.info("MenuController | retrieveMenu | type :: " + menuDto.toString());
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", menuDto);
		
		return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
	}
	
	@GetMapping( "/retrieveType/{foodType}" )
	public ResponseEntity<Object> retrieveMenu(@PathVariable String foodType) {
		logger.info("MenuController | retrieve - retrieveMenu | START");
		MenuDto menuDto = menuService.retreiveMenu(foodType);
		logger.info("MenuController | retrieveMenu | type :: " + menuDto.toString());
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", menuDto);
		
		return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
	}
	
	@PostMapping( "/menufortoday/{menuId}" )
	public ResponseEntity<Object> addForToday(@PathVariable Long menuId) {
		String alertMessage = menuService.addMenuForToday(menuId);
		logger.info("MenuController | retrieveMenu | alertMessage :: " + alertMessage);
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", alertMessage);
		
		return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
	}
	
	@DeleteMapping( "/menufortoday/{menuId}" )
	public ResponseEntity<Object> deleteForToday(@PathVariable Long menuId) {
		String alertMessage = menuService.deleteMenuForToday(menuId);
		logger.info("MenuController | retrieveMenu | alertMessage :: " + alertMessage);
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", alertMessage);
		
		return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
	}
}
