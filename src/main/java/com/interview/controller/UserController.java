package com.interview.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.config.jwtUtils;
import com.interview.dto.AuthenticationRequest;
import com.interview.dto.AuthenticationResponse;
import com.interview.dto.signupRequest;
import com.interview.dto.userDto;
import com.interview.entity.User;
import com.interview.repository.UserRepository;
import com.interview.service.UserService;
import com.interview.serviceImpl.UserJetService;

@RestController
@RequestMapping("/user/")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserJetService jwtSecurityService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private jwtUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository; 
	
	@PostMapping("create-user")
	public ResponseEntity<?> createUser(@RequestBody signupRequest signupRequest){
		if(userService.hasUserWithUsername(signupRequest.getUsername()))
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("USERNAME_ALREADY_EXIST");
		}
		userDto dto=userService.createUser(signupRequest);
		if(dto==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@PostMapping("login")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
				BadCredentialsException, DisabledException, UsernameNotFoundException
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e)
		{
			throw new BadCredentialsException("Incorrect Username or password");
		}
		
		final UserDetails userDetails=jwtSecurityService.userDetailsService().loadUserByUsername(authenticationRequest.getUsername());
		Optional<User> optionalUser=userRepository.findFirstByUsername(userDetails.getUsername());
		
		final String jwt=jwtUtils.generateToken(userDetails);
		AuthenticationResponse authenticationResponse=new AuthenticationResponse();
		if(optionalUser.isPresent())
		{
			authenticationResponse.setJwt(jwt);
			authenticationResponse.setUserId(optionalUser.get().getUserId());
			authenticationResponse.setUserRoles(optionalUser.get().getUserRole()); 
		}
		
		return authenticationResponse;
	}
}
