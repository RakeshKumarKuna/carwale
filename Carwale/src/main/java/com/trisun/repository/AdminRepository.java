package com.trisun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trisun.entity.Admin;
import com.trisun.entity.User;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
  
	 Optional<Admin>  findByUser(User user);
}
