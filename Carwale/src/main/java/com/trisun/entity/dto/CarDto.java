package com.trisun.entity.dto;

import com.trisun.core.FuelType;
import com.trisun.entity.Brand;
import com.trisun.entity.Category;
import com.trisun.entity.Image;
import com.trisun.entity.Seller;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

	

	private String name;

	private String currentLocation;

	private Double price;

	private String model;//

	private String description;
	
	private String fuelType;
	
	private Integer carNumber;

	private Long contact;
	
	private Integer pincode;

	private String brand;

	private String category;

	private String image;
	
	private Seller sellerId;
}
