package com.trisun.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuyerDto {
	
	private Integer id;
	private String email;
	private String fullName;
	private String token;
		
	
}
