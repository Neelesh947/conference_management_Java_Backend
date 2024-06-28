package com.interview.service;

import java.util.List;

import com.interview.entity.Conferences;

public interface ConferenceService {
	
	public List<Conferences> getAllConference();
	
	public Conferences saveConference(Conferences conferences);
	
	public Conferences getConfernecByName(String name);
	
	public Conferences getConferenecById(Long id);
}
