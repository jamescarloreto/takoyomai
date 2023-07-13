package com.petsimx.takoyomai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table ( name = "USER_INFORMATION_DETAILS" )
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInformationDetail {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Integer userId;
	
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String phoneNo;
	private String password;
	private String role;
}
