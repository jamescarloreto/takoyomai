package com.petsimx.takoyomai.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.petsimx.takoyomai.bean.UserInfoDetailsBean;
import com.petsimx.takoyomai.exceptions.EmailNotFoundException;
import com.petsimx.takoyomai.model.UserInformationDetail;
import com.petsimx.takoyomai.repository.UserInformationDetailsRepository;

@Component
public class UserInformationDetailsServiceImpl implements UserDetailsService {
	
	private UserInformationDetailsRepository userInformationDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserInformationDetail> userInformationDetails = userInformationDetailsRepository.findByEmail(username);
		
		return userInformationDetails.map(UserInfoDetailsBean::new).orElseThrow(() -> new EmailNotFoundException("Email " + username + " not found!"));
	}
}
