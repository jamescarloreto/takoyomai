package com.petsimx.takoyomai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petsimx.takoyomai.dto.UserInfoDetailsDto;
import com.petsimx.takoyomai.model.UserInformationDetail;
import com.petsimx.takoyomai.repository.UserInformationDetailsRepository;
import com.petsimx.takoyomai.service.EmailService;
import com.petsimx.takoyomai.service.UserInfoDetailsService;

@Service
public class UserInfoDetailsServiceImpl implements UserInfoDetailsService {
	
	@Autowired
	private UserInformationDetailsRepository userInformationDetailsRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;

	@Override
	public UserInformationDetail addUserDetails(UserInfoDetailsDto userInfoDetailDto) {
		
		UserInformationDetail userInformationDetail = new UserInformationDetail();
		
		if (userInfoDetailDto.getFromAdmin()) {
			userInformationDetail.setRole("ROLE_USER");
		} else {
			userInformationDetail.setRole("ROLE_CUSTOMER");
		}
		
		userInformationDetail.setPassword(passwordEncoder.encode(userInfoDetailDto.getUserInformationDetail().getPassword()));
		
		if (!userInfoDetailDto.getFromAdmin())
			sendEmailUser(userInfoDetailDto.getUserInformationDetail().getEmail());
		
		return userInformationDetailsRepository.save(userInformationDetail);
	}
	
	public void sendEmailUser(String email) {
		String subject = "Welcome to Takoyomai!";
		String message = "Hope this message finds you well! \n\nYou successfully created your <b>Takoyomai</b> account. "
				+ "Feel free to order your favorite food anytime and if you have any question or concern, please go to our website <link href='http://localhost:8080'> "
				+ "Takoyomai</link>. Thank you. \n\n\n This is a email notification, please do not reply!!!";
		
		emailService.sendEmail(email, subject, message);
	}
}
