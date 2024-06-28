package com.interview.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.interview.repository.UserRepository;

@Service
public class UserJwtSecurityService implements UserJetService{

	@Autowired
	private UserRepository userRepository;
	
	public UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				return userRepository.findFirstByUsername(username)
							.orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
			}
		};
	}

}

