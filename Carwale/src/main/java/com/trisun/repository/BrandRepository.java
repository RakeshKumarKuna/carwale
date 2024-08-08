package com.trisun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trisun.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
 
	Optional<Brand> findByName(String name);
	
	
}
