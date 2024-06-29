package com.exam.examserver2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examserver2.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
