package com.petsimx.takoyomai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petsimx.takoyomai.dto.AjaxResponse;
import com.petsimx.takoyomai.dto.OrderDto;
import com.petsimx.takoyomai.service.impl.OrderService;

@RestController
@RequestMapping( "/order" ) 
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping( "/placeorder/{menuId}" )
	public ResponseEntity<Object> placeOrder(@PathVariable long menuId) {
		
		String placeOrderMsg = orderService.placeOrder(menuId);
		
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", placeOrderMsg);
		return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
	}
	
	@GetMapping( "/showorder" )
	public ResponseEntity<Object> showOrder() {
		
		OrderDto orderDto = orderService.showOrder();
		
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", orderDto);
		return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
	}
	
	@DeleteMapping( "/removeorder/{menuId}" )
	public ResponseEntity<Object> removeOrder(@PathVariable long menuId) {
		
		String removedMsg = orderService.removeOrder(menuId);
		
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", removedMsg);
		return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
	}
	
	@PostMapping( "/checkout" )
	public ResponseEntity<Object> checkOut() {
		
		String checkOutMsg = orderService.checkOutOrder();
		
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>("success", checkOutMsg);
		return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK); 
	}
}
