package com.petsimx.takoyomai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petsimx.takoyomai.dto.AjaxResponse;
import com.petsimx.takoyomai.dto.UserInfoDetailsDto;
import com.petsimx.takoyomai.exceptions.EmailOrPasswordNotMatchException;
import com.petsimx.takoyomai.model.UserInformationDetail;
import com.petsimx.takoyomai.service.UserInfoDetailsService;

@Controller
@RequestMapping
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	

	@Autowired
	private UserInfoDetailsService userInfoDetailsService;
	
	@GetMapping("/")
	public String index() {
		
		return "index";
	}

	@PostMapping( "/login" )
	public ResponseEntity<AjaxResponse<Object>> loginUser(@RequestBody UserInfoDetailsDto userInfoDetailDto) {
		
		logger.info("IndexController | loginUser | userInfoDetailDto :: " + userInfoDetailDto);
		
		try {
			UserInformationDetail userInfoDetail = userInfoDetailsService.addUserDetails(userInfoDetailDto);
			AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", userInfoDetail);
			
			return new ResponseEntity<AjaxResponse<Object>>(ajaxResponse, HttpStatus.OK);
		} catch (EmailOrPasswordNotMatchException e) {
			logger.error("Error:: " + e.getMessage());
			
			AjaxResponse<Object> ajaxErrorResponse = new AjaxResponse<Object>();

			return new ResponseEntity<AjaxResponse<Object>>(ajaxErrorResponse, HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
