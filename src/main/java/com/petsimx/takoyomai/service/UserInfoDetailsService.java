package com.petsimx.takoyomai.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.petsimx.takoyomai.dto.UserInfoDetailsDto;
import com.petsimx.takoyomai.model.UserInformationDetail;

public interface UserInfoDetailsService {

	UserInformationDetail addUserDetails(UserInfoDetailsDto userInfoDetailDto);

	UserDetails findByEmail(String username);
}
