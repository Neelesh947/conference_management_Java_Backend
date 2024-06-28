package com.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.interview.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long>{

	 List<Registration> findAllByConferences_ConfId(@Param("conferenceId") Long conferenceId);
}
