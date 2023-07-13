package com.petsimx.takoyomai.service;

public interface EmailService {
	
	void sendEmail(String toEmail, String subject, String message);
}
