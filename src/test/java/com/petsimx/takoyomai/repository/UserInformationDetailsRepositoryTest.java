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

import com.petsimx.takoyomai.exceptions.EmailNotFoundException;
import com.petsimx.takoyomai.model.UserInformationDetail;

@DataJpaTest
@AutoConfigureTestDatabase (connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource (locations = "classpath:application-test.properties" )
class UserInformationDetailsRepositoryTest {
	
	private UserInformationDetailsRepository userInformationDetailsRepository;
	
	private UserInformationDetail userInfoDetailBuilder;
	
	@Autowired
	public UserInformationDetailsRepositoryTest(UserInformationDetailsRepository userInformationDetailsRepository) {
		
		this.userInformationDetailsRepository = userInformationDetailsRepository;
	}
	
	@BeforeEach
	public void arrangeMethod() {
		//Arrange
		userInfoDetailBuilder = UserInformationDetail.builder()
				.firstName("Juan")
				.lastName("Dela Cruz")
				.email("sample@gmail.com")
				.phoneNo("09090909090")
				.password("pass")
				.build();
	}
	
	@Test
	void testFindByEmail() {
		
		//Act
		userInformationDetailsRepository.save(userInfoDetailBuilder);
		UserInformationDetail userInformationDetail = userInformationDetailsRepository.findByEmail("sample@gmail.com").orElseThrow(
				() -> new EmailNotFoundException("Email not existing to the database ...")
		);
		
		//Assert
		Assertions.assertThat(userInformationDetail).isNotNull();
	}

	

}
