package com.trisun.entity.dto;

import org.springframework.web.multipart.MultipartFile;

import com.trisun.entity.Image;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandDto {

	private String brandName;

	private String imageUrl;
	
	private Boolean isFeatured;
	
	
}
