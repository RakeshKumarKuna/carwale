package com.trisun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trisun.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

   Optional<Image> findByUrl(String url);

}
