package com.exam.examserver2.controller;

import java.security.Principal;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver2.config.JwtUtils;
import com.exam.examserver2.entity.JwtRequest;
import com.exam.examserver2.entity.JwtResponse;
import com.exam.examserver2.entity.User;
import com.exam.examserver2.service.Impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	
	//generate token
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(" not found");
		}
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
	    String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	private void authenticate(String username, String password) throws Exception {
		
		try {
			authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
			
			
		}
		catch(DisabledException e)
		{
			throw new Exception("User Disabled" +e.getMessage());
		}
		catch(BadCredentialsException e) {
			throw new Exception("Invalid credentials" +e.getMessage());
		}
		
	}
	
	//return the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal){
	return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
	}

}
