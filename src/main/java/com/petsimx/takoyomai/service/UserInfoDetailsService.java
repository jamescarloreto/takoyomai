package com.petsimx.takoyomai.service;

import com.petsimx.takoyomai.dto.UserInfoDetailsDto;
import com.petsimx.takoyomai.model.UserInformationDetail;

public interface UserInfoDetailsService {

	UserInformationDetail addUserDetails(UserInfoDetailsDto userInfoDetailDto);
}
