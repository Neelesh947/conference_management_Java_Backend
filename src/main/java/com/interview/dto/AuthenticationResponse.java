package com.interview.dto;

import com.interview.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {

	private String jwt;
	
	private UserRole  userRoles;
	
	private String userId;
}
