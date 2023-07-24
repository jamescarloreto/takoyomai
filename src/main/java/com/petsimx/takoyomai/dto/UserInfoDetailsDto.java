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
	
	public UserInformationDetail userInformationDetail = new UserInformationDetail();
	public Boolean fromAdmin;
	public String username;
	public String password;
	public String firstName;
	public String lastName;
}
