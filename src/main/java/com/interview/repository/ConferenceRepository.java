package com.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.entity.Conferences;

public interface ConferenceRepository extends JpaRepository<Conferences, Long>{

	public Conferences findByName(String name);
}
