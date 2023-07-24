package com.petsimx.takoyomai.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petsimx.takoyomai.dto.AjaxResponse;
import com.petsimx.takoyomai.dto.UserInfoDetailsDto;
import com.petsimx.takoyomai.service.UserInfoDetailsService;

@RestController
@RequestMapping( "/userevent" )
public class UserEventController {

	private static final Logger logger = LoggerFactory.getLogger(UserEventController.class);
	
	@Autowired
	private UserInfoDetailsService userInfoDetailsService;
	
	@GetMapping( "/getUserDetails" )
	public ResponseEntity<AjaxResponse<Object>> principalUser() {
		logger.info("UserEventController | principalUser ");
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		
		try {
			UserInfoDetailsDto userInfoDetailsDto = userInfoDetailsService.getUserDetails();
			ajaxResponse = new AjaxResponse<Object>("success", userInfoDetailsDto);
			logger.info("UserEventController | user :: " + userInfoDetailsDto.toString());
			
			return new ResponseEntity<AjaxResponse<Object>>(ajaxResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error:: " + e.getMessage());
			
			return new ResponseEntity<AjaxResponse<Object>>(ajaxResponse, HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
