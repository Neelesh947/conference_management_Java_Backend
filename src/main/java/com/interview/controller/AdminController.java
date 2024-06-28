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
@RequestMapping("/admin/")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private ConferenceService conferenceService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private FeedBackService feedBackService;
	
	@PostMapping("conference")
	public ResponseEntity<Conferences> createConference(@RequestBody Conferences conferences)
	{
		Conferences conferences2=this.conferenceService.saveConference(conferences);
		return ResponseEntity.status(HttpStatus.CREATED).body(conferences2);
	}
	
	@GetMapping("conference")
	public ResponseEntity<List<Conferences>> getAllConference(){
		List<Conferences> conf=this.conferenceService.getAllConference();
		return ResponseEntity.status(HttpStatus.OK).body(conf);
	}
	
	@GetMapping("conference/{id}")
	public ResponseEntity<Conferences> getConferenceByID(@PathVariable Long id){
		Conferences conferences=conferenceService.getConferenecById(id);
		return ResponseEntity.status(HttpStatus.OK).body(conferences);
	}
	
	@GetMapping("conference/{id}/registration")
	public ResponseEntity<List<Registration>> getRegistrationByConference(@PathVariable Long id){
		List<Registration> registration=registrationService.getRegistrationListByConference(id);
		return ResponseEntity.status(HttpStatus.OK).body(registration);
	}
	
	@GetMapping("conference/{id}/feedback")
	public ResponseEntity<List<Feedback>> getFeedBackByConferences(@PathVariable Long id){
		List<Feedback> feedback=feedBackService.getFeedbackByConference(id);
		return ResponseEntity.status(HttpStatus.OK).body(feedback);
	}
}
