package com.exam.examserver2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examserver2.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {


public User findByUsername(String username);
}
