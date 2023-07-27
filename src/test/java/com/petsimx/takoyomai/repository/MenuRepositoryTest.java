package com.petsimx.takoyomai.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.petsimx.takoyomai.model.Menu;

@DataJpaTest
@AutoConfigureTestDatabase( connection = EmbeddedDatabaseConnection.H2 )
@TestPropertySource (locations = "classpath:application-test.properties" )
class MenuRepositoryTest {
	
	private MenuRepository menuRepository;
	
	private Menu menuBuilder;
	
	@Autowired
	public MenuRepositoryTest(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}
	
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
	void testSave() {
		//Act
		Menu menu = menuRepository.save(menuBuilder);
		
		Assertions.assertThat(menu).isNotNull();
	}

}
