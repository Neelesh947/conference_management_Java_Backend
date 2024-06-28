package com.interview.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.entity.Feedback;
import com.interview.repository.FeedbackRepository;
import com.interview.service.FeedBackService;

@Service
public class FeedbackServiceImpl implements FeedBackService{

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	public Feedback submitFeedback(Feedback feedback)
	{
		return feedbackRepository.save(feedback);
	}
	
	public List<Feedback> getFeedbackByConference(Long conferenceId)
	{
		return feedbackRepository.findAllByConferences_ConfId(conferenceId);
	}
}
