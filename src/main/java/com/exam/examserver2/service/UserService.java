package com.exam.examserver2.service;

import java.util.Set;

import com.exam.examserver2.entity.User;
import com.exam.examserver2.entity.UserRole;

public interface UserService {

	//creating user
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;

	//get userby username
	public User getUser(String username);
	
	//get delete user by id
	public void deleteUser(Long userId);
}
