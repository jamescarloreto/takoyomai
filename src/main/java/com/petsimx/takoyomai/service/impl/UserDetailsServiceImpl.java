package com.petsimx.takoyomai.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.petsimx.takoyomai.service.UserInfoDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserInfoDetailsService userInfoDetailsService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("UserDetailsServiceImpl | loadUserByUsername | username :: " + username);
		
		return userInfoDetailsService.findByEmail(username);
				
	}
}
