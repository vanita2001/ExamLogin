package com.exam.examserver2;



import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.examserver2.entity.Role;
import com.exam.examserver2.entity.User;
import com.exam.examserver2.entity.UserRole;
import com.exam.examserver2.service.UserService;

@SpringBootApplication
public class Examserver2Application implements CommandLineRunner
	{
	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(Examserver2Application.class, args);
		
	}
	@Override
	public void run(String... args) throws Exception{
		System.out.println("Starting code");
		
//		User user = new User();
//		user.setFirstName("vanita");
//		user.setLastName("Ingle");
//		user.setUsername("vanita2001");
//		user.setPassword("");
//		user.setEmail("vanita@gmail.com");
//		user.setProfile("default.png");
//		
//		Role role1= new Role();
//		role1.setRoleId(44L);
//		role1.setRoleName("ADMIN");
//		
//		Set<UserRole> userRoleSet =  new HashSet<>();
//		UserRole userRole =  new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		
//		userRoleSet.add(userRole);
//		
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUsername());	
	}

}
