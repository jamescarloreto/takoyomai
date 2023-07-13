package com.petsimx.takoyomai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petsimx.takoyomai.dto.AjaxResponse;
import com.petsimx.takoyomai.dto.UserInfoDetailsDto;
import com.petsimx.takoyomai.model.UserInformationDetail;
import com.petsimx.takoyomai.service.UserInfoDetailsService;

@RestController
@RequestMapping( "/user" )
public class UserInfoDetailsController {
	
	@Autowired
	private UserInfoDetailsService userInfoDetailsService;
	
	@PostMapping( "/create" )
	public ResponseEntity<AjaxResponse<Object>> createUser(@RequestBody UserInfoDetailsDto userInfoDetailDto) {
		
		UserInformationDetail userInfoDetail = userInfoDetailsService.addUserDetails(userInfoDetailDto);
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", userInfoDetail);
		
		return new ResponseEntity<AjaxResponse<Object>>(ajaxResponse, HttpStatus.OK);
	}
	
	
}
