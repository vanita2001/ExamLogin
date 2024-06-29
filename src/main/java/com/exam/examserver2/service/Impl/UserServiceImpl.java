package com.exam.examserver2.service.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver2.entity.User;
import com.exam.examserver2.entity.UserRole;
import com.exam.examserver2.repo.RoleRepository;
import com.exam.examserver2.repo.UserRepository;
import com.exam.examserver2.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception  {
		
		User local = this.userRepository.findByUsername(user.getUsername());
		if(local != null) {
			System.out.println("User is already there !!");
			throw new UserAlreadyExistsException("User Already present !!");
		}
		else {
			//user create
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		return local;
	}
	
	//getting user
	@Override
	public User getUser(String username)
	{
		return this.userRepository.findByUsername(username);
	}

	//delete user
	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
		
	}

}
