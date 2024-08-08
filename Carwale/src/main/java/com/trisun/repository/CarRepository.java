package com.trisun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trisun.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
