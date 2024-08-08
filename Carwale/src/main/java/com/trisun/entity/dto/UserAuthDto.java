package com.trisun.entity.dto;

import com.trisun.entity.UserType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDto {

	//@NotNull
	private String fullName;
	@NotNull
	private String email;
	@NotNull
	private String password;	
	
	private String userRole;
	
}
