package com.petsimx.takoyomai.service;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.petsimx.takoyomai.dto.UserInfoDetailsDto;
import com.petsimx.takoyomai.model.UserInformationDetail;
import com.petsimx.takoyomai.repository.UserInformationDetailsRepository;
import com.petsimx.takoyomai.service.impl.UserInfoDetailsServiceImpl;

@Import({ConfigTest.class})
@ExtendWith(MockitoExtension.class)
class UserInfoDetailsServiceTest {
	
	@Mock
	private UserInformationDetailsRepository userInformationDetailsRepository;
	
	@InjectMocks
	private UserInfoDetailsServiceImpl userInfoDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private UserInformationDetail userInfoDetailBuilder;
	private UserInfoDetailsDto userInfoDetailsDto;
	
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
		
		userInfoDetailsDto = UserInfoDetailsDto.builder()
				.userInformationDetail(userInfoDetailBuilder)
				.fromAdmin(true)
				.build();
		
	}
	
	@Test
	void testAddUserDetails() {
		//Act
		when(userInformationDetailsRepository.save(userInfoDetailBuilder)).thenReturn(userInfoDetailBuilder);
		//when(passwordEncoder.encode(userInfoDetailBuilder.getPassword())).thenReturn(userInfoDetailBuilder.getPassword());
		
		UserInformationDetail userInformationDetail = userInfoDetailsService.addUserDetails(userInfoDetailsDto);
		//Assert
		Assertions.assertThat(userInformationDetail).isNotNull();
	}

}
