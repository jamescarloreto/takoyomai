package com.petsimx.takoyomai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AjaxResponse<T> {
	
	private String status;
	private T object;

}
