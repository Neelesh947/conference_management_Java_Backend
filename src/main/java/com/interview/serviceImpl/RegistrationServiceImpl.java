package com.interview.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.entity.Registration;
import com.interview.repository.RegistrationRepository;
import com.interview.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private RegistrationRepository registrationRepository;
	
	public Registration registerUser(Registration registration)
	{
		return registrationRepository.save(registration);
	}
	
	public List<Registration> getRegistrationListByConference(Long confernceId){
		return registrationRepository.findAllByConferences_ConfId(confernceId);
	}
}
