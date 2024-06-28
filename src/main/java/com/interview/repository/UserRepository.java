package com.interview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.entity.User;
import com.interview.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findFirstByUsername(String username);
	
	public User findByUsername(String username);
	
	public User findByUserRole(UserRole userRole);
}
