package com.interview.dto;

import com.interview.enums.UserRole;

import lombok.Data;

@Data
public class userDto {

	private String userId;
	private String name;
	private String username;
	private String password;
	private UserRole userRole;
}
