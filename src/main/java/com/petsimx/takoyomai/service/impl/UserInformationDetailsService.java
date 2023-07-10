package com.petsimx.takoyomai.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.petsimx.takoyomai.dto.UserInfoDetailsDto;
import com.petsimx.takoyomai.exceptions.EmailNotFoundException;
import com.petsimx.takoyomai.model.UserInformationDetails;
import com.petsimx.takoyomai.repository.UserInformationDetailsRepository;

@Component
public class UserInformationDetailsService implements UserDetailsService {
	
	private UserInformationDetailsRepository userInformationDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserInformationDetails> userInformationDetails = userInformationDetailsRepository.findByEmail(username);
		
		return userInformationDetails.map(UserInfoDetailsDto::new).orElseThrow(() -> new EmailNotFoundException("Email " + username + " not found!"));
	}
}
