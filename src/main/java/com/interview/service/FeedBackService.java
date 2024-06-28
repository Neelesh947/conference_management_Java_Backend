package com.interview.service;

import java.util.List;

import com.interview.entity.Feedback;

public interface FeedBackService {

	public Feedback submitFeedback(Feedback feedback);
	
	public List<Feedback> getFeedbackByConference(Long conferenceId);
}
