package com.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.entity.Conferences;
import com.interview.entity.Feedback;
import com.interview.entity.Registration;
import com.interview.service.ConferenceService;
import com.interview.service.FeedBackService;
import com.interview.service.RegistrationService;

@RestController
@RequestMapping("/customer/")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private ConferenceService conferenceService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private FeedBackService feedBackService;
	
	@GetMapping("conference")
	public ResponseEntity<List<Conferences>> getAllConferences(){
		List<Conferences> conf=this.conferenceService.getAllConference();
		return ResponseEntity.status(HttpStatus.OK).body(conf);
	}
	
	@GetMapping("conference/{id}")
	public ResponseEntity<Conferences> getConferenceById(@PathVariable Long id){
		Conferences conf=this.conferenceService.getConferenecById(id);
		return ResponseEntity.status(HttpStatus.OK).body(conf);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Registration> registerUser(@RequestBody Registration registration)
	{
		Registration registerUser=registrationService.registerUser(registration);
		return ResponseEntity.status(HttpStatus.CREATED).body(registerUser);
	}
	
	@PostMapping("/feedback")
	public ResponseEntity<Feedback> submitFeedback(@RequestBody Feedback feedback)
	{
		Feedback feedbackuser=feedBackService.submitFeedback(feedback);
		return ResponseEntity.status(HttpStatus.CREATED).body(feedbackuser);
	}
}
