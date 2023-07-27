package com.petsimx.takoyomai.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petsimx.takoyomai.model.Menu;
import com.petsimx.takoyomai.service.MenuService;

@WebMvcTest (controllers = MenuController.class)
@AutoConfigureMockMvc (addFilters = false)
@ExtendWith (MockitoExtension.class)
class MenuControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MenuService menuService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	Menu menuBuilder;
	
	@BeforeEach
	public void beforeEachMethod() {
		//Arrange
		menuBuilder = Menu.builder()
				.name("Adobo")
				.price(51.50)
				.type("Dish")
				//.fileName("adobo")
				.description("Meat")
				.build();
	}

	@Test
	void testSaveMenu() throws Exception {
//		given(menuService.saveMenu(any(Menu.class))).willAnswer(
//				(invocation) -> invocation.getArgument(0)
//		);
		
		mockMvc.perform(post("/menu/create")
		.contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(menuBuilder)))
		.andExpect(status().isCreated());
		//.andExpect(jsonPath("$.menu", is(menuBuilder)));
	}

}
