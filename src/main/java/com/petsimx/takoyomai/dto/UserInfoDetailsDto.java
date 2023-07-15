package com.petsimx.takoyomai.dto;

import com.petsimx.takoyomai.model.UserInformationDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserInfoDetailsDto {
	
	private UserInformationDetail userInformationDetail = new UserInformationDetail();
	private Boolean fromAdmin;
	private String username;
	private String password;
}
