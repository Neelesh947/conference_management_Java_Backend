package com.interview.service;

import com.interview.dto.signupRequest;
import com.interview.dto.userDto;

public interface UserService {

	public userDto createUser(signupRequest signupRequest) ;
	
	public boolean hasUserWithUsername(String username);
}
