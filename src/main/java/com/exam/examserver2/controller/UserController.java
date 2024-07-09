package com.exam.examserver2.controller;

import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver2.entity.Role;
import com.exam.examserver2.entity.User;
import com.exam.examserver2.entity.UserRole;
import com.exam.examserver2.helper.UserNotFountException;
import com.exam.examserver2.service.UserService;

@RestController

@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//creating user
	@PostMapping("/")   //to save data postmapping
	public User createUser(@RequestBody User user) throws Exception //accept data through request body because format is json
	{
		
		user.setProfile("default.png");
		Set<UserRole> roles =new HashSet<>();
		//because not create parameterize conster of Role
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole= new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		return this.userService.createUser(user, roles);
		
	}
	//get by username
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username)
	{
		return this.userService.getUser(username);
	}
	
	//delete the user by id
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")Long userId) {
		this.userService.deleteUser(userId);
		
	}
	
	//update api
	
	@ExceptionHandler(UserNotFountException.class)
	public ResponseEntity <?> exceptionHandler(UserNotFountException ex){
		return null;
		
	}
	
	
}
