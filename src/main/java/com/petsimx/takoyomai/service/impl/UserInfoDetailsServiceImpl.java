package com.petsimx.takoyomai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petsimx.takoyomai.model.UserInformationDetails;
import com.petsimx.takoyomai.repository.UserInformationDetailsRepository;
import com.petsimx.takoyomai.service.UserInfoDetailsService;

@Service
public class UserInfoDetailsServiceImpl implements UserInfoDetailsService {
	
	@Autowired
	private UserInformationDetailsRepository userInformationDetailsRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String addUserDetails(UserInformationDetails userInformationDetails) {
		
		userInformationDetails.setPassword(passwordEncoder.encode(userInformationDetails.getPassword()));
		userInformationDetailsRepository.save(userInformationDetails);
		
		return "User added to system";
	}
}
