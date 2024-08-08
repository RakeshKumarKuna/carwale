package com.trisun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trisun.entity.Seller;
import com.trisun.entity.User;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
	 Optional<Seller>  findByUser(User user);
}
