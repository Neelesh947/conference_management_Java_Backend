package com.interview.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.entity.Conferences;
import com.interview.repository.ConferenceRepository;
import com.interview.service.ConferenceService;

@Service
public class ConferenceServiceImpl implements ConferenceService{

	@Autowired
	private ConferenceRepository conferenceRepository;
	
	public List<Conferences> getAllConference(){
		return conferenceRepository.findAll();
	}
	
	public Conferences saveConference(Conferences conferences) {
		return  conferenceRepository.save(conferences);
	}
	
	public Conferences getConfernecByName(String name) {
		return conferenceRepository.findByName(name);
	}
	
	public Conferences getConferenecById(Long id) {
		return conferenceRepository.findById(id).orElse(null);
	}
}
