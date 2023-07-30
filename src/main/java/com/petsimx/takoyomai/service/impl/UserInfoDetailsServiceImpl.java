package com.petsimx.takoyomai.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petsimx.takoyomai.bean.UserInfoDetailsBean;
import com.petsimx.takoyomai.dto.UserInfoDetailsDto;
import com.petsimx.takoyomai.exceptions.EmailExistingException;
import com.petsimx.takoyomai.exceptions.EmailNotFoundException;
import com.petsimx.takoyomai.model.UserInformationDetail;
import com.petsimx.takoyomai.repository.UserInformationDetailsRepository;
import com.petsimx.takoyomai.service.EmailService;
import com.petsimx.takoyomai.service.UserInfoDetailsService;
import com.petsimx.takoyomai.utils.UserDetailUtils;

@Service
public class UserInfoDetailsServiceImpl implements UserInfoDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoDetailsServiceImpl.class);
	
	@Autowired
	private UserInformationDetailsRepository userInformationDetailsRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public UserInformationDetail addUserDetails(UserInfoDetailsDto userInfoDetailDto) {
		logger.info("addUserDetails | userInfoDetailDto :: " + userInfoDetailDto);
		
		UserInformationDetail userInformationDetail = userInfoDetailDto.getUserInformationDetail();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		
		if (userInformationDetailsRepository.findByEmail(userInformationDetail.getEmail()).isPresent()) {
			throw new EmailExistingException("Email existing!");
		}
		
		List<UserInformationDetail> UserInfoDetails = userInformationDetailsRepository.findAll();
			
		if (UserInfoDetails.isEmpty()) {
			userInformationDetail.setRole("ROLE_ADMIN");
		} else if (!username.isEmpty()) {
			userInformationDetail.setRole("ROLE_USER");
		} else {
			userInformationDetail.setRole("ROLE_CUSTOMER");
		}
		
		userInformationDetail.setPassword(passwordEncoder.encode(userInfoDetailDto.getUserInformationDetail().getPassword()));
		
		if (!username.isEmpty())
			sendEmailUser(userInfoDetailDto.getUserInformationDetail().getEmail());
		
		return userInformationDetailsRepository.save(userInformationDetail);
	}
	
	public void sendEmailUser(String email) {
		String subject = "Welcome to Takoyomai!";
		String message = "Hope this message finds you well! \n\nYou successfully created your Takoyomai account. "
				+ "Feel free to order your favorite food anytime and if you have any question or concern, please go to our website "
				+ "Takoyomai. Thank you. \n\n\n This is a email notification, please do not reply!!!";
		
		emailService.sendEmail(email, subject, message);
	}

	@Override
	public UserDetails findByEmail(String username) {
		
		Optional<UserInformationDetail> userInformationDetails = userInformationDetailsRepository.findByEmail(username);
		
		return userInformationDetails.map(UserInfoDetailsBean::new).orElseThrow(() -> new EmailNotFoundException("Email " + username + " not found!"));
	}

	@Override
	public UserInfoDetailsDto getUserDetails() {
		logger.info("UserInfoDetailsServiceImpl | getUserDetails | START");
		try {
			String username = UserDetailUtils.getUsername();
		    
		    logger.info("UserInfoDetailsServiceImpl | getUserDetails | username :: " + username);
		    
		    UserInformationDetail userInformationDetail = userInformationDetailsRepository.findByEmail(username).orElseThrow(() -> new EmailNotFoundException("Email :: " + username + "  not found..."));
			
		    return UserInfoDetailsDto.builder()
		    		.firstName(userInformationDetail.getFirstName())
		    		.lastName(userInformationDetail.getLastName())
		    		.username(userInformationDetail.getEmail())
		    		.build();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			logger.info("UserInfoDetailsServiceImpl | getUserDetails | END");
		}
		return null;
	}
	
	
}
