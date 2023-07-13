package com.petsimx.takoyomai.dto;

import com.petsimx.takoyomai.model.UserInformationDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDetailsDto {
	
	private UserInformationDetail userInformationDetail = new UserInformationDetail();
	private Boolean fromAdmin;
}
