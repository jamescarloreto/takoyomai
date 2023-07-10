package com.petsimx.takoyomai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petsimx.takoyomai.model.UserInformationDetails;
import com.petsimx.takoyomai.service.UserInfoDetailsService;

@RestController
@RequestMapping( "/user" )
public class UserInfoDetailsController {
	
	@Autowired
	private UserInfoDetailsService userInfoDetailsService;
	
	@PostMapping( "/create" )
	public ResponseEntity<String> createUser(@RequestBody UserInformationDetails userInformationDetails) {
		
		return new ResponseEntity<String>(userInfoDetailsService.addUserDetails(userInformationDetails), HttpStatus.OK);
	}
}
