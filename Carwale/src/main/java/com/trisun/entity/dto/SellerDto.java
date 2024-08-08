package com.trisun.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SellerDto {

	private Integer id;
	private String email;
	private String fullName;
	private String token;
}
