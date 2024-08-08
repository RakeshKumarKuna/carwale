package com.trisun.entity.dto;

import com.trisun.core.FuelType;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDto {
	
	private String name;
	private String number;
	private String description;
	private Long  contact;
	private FuelType fuelType;
	private Double price;
	private String model;
	private String image;
	private String location;
	private String pincode;

}
