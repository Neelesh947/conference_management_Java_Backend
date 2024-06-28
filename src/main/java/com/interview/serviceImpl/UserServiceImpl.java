package com.interview.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.interview.dto.signupRequest;
import com.interview.dto.userDto;
import com.interview.entity.User;
import com.interview.enums.UserRole;
import com.interview.repository.UserRepository;
import com.interview.service.UserService;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void createAdminAccount() {
		User userAdmin=userRepository.findByUserRole(UserRole.ADMIN);
		if(userAdmin==null)
		{
			String uuid=UUID.randomUUID().toString();
			User newUserAdmin=new User();
			newUserAdmin.setName("Admin");
			newUserAdmin.setUsername("Admin");
			newUserAdmin.setPassword(new BCryptPasswordEncoder().encode("ADMIN"));
			newUserAdmin.setUserId(uuid);
			newUserAdmin.setUserRole(UserRole.ADMIN);
			userRepository.save(newUserAdmin);
			
			System.out.println("Admin account has been created");
		}
	}
	
	//create user
	
	public userDto createUser(signupRequest signupRequest) {
		String uuid=UUID.randomUUID().toString();
		User user=new User();
		user.setName(signupRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setUsername(signupRequest.getUsername());
		user.setUserId(uuid);
		user.setUserRole(UserRole.USER);
		User createdUser=userRepository.save(user);
		
		userDto dto=new userDto();
		dto.setUserId(createdUser.getUserId());
		dto.setName(createdUser.getName());
		dto.setPassword(createdUser.getPassword());
		dto.setUsername(createdUser.getUsername() );
		dto.setUserRole(createdUser.getUserRole());
		return dto;		
	}
	
	public boolean hasUserWithUsername(String username) {
		return userRepository.findFirstByUsername(username).isPresent();
	}
	
}
