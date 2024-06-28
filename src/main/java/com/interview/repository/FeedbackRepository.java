package com.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.interview.entity.Feedback;

public interface FeedbackRepository  extends JpaRepository<Feedback, Long>{

	List<Feedback> findAllByConferences_ConfId(@Param("conferenceId") Long conferenceId);
}
