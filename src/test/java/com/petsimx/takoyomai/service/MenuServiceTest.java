package com.petsimx.takoyomai.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petsimx.takoyomai.model.Menu;
import com.petsimx.takoyomai.repository.MenuRepository;
import com.petsimx.takoyomai.service.impl.MenuServiceImpl;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {
	
	@Mock
	private MenuRepository menuRepository;
	
	@InjectMocks
	MenuServiceImpl menuService;
	
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
	void testSaveMenu() {
		
		when(menuRepository.save(menuBuilder)).thenReturn(menuBuilder);
		//Act
		//Menu menu = menuService.saveMenu(menuBuilder);
		
		//Assert
		//Assertions.assertThat(menu).isNotNull();
	}

}
