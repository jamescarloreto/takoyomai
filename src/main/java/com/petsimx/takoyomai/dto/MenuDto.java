package com.petsimx.takoyomai.dto;

import org.springframework.web.multipart.MultipartFile;

import com.petsimx.takoyomai.model.Menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDto {
	
	private Menu menu = new Menu();
	private MultipartFile file;
}
