package com.interview.service;

import java.util.List;

import com.interview.entity.Registration;

public interface RegistrationService {

	public Registration registerUser(Registration registration);
	
	public List<Registration> getRegistrationListByConference(Long confernceId);
}
