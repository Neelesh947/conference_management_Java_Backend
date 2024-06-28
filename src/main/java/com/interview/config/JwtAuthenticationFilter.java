package com.interview.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.interview.serviceImpl.UserJwtSecurityService;
import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private jwtUtils jwtUtils;
	
	@Autowired
	private UserJwtSecurityService userService;
	
	@Override
	protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {		
		final String authHeader=request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		
		if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,"Bearer"))
		{
			filterChain.doFilter(request, response);
			return;
		}	
		
		jwt=authHeader.substring(7);
		userEmail=jwtUtils.extractUsername(jwt);
		
		if(StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails=userService.userDetailsService().loadUserByUsername(userEmail);
			if(jwtUtils.IsTokenvalid(jwt, userDetails))
			{
				SecurityContext context=SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				context.setAuthentication(authToken);
				SecurityContextHolder.setContext(context);
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
