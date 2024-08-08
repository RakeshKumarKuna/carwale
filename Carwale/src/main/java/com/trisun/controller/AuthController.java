package com.trisun.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trisun.entity.User;
import com.trisun.entity.dto.UserAuthDto;
import com.trisun.response.ApiResponse;
import com.trisun.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class AuthController {

	private final UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> loginUser(@RequestBody @Valid UserAuthDto userRegisterDto) {
		ApiResponse apiResponse = userService.login(userRegisterDto);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse>  registerUser(@RequestBody @Valid UserAuthDto userRegisterDto) {
		 ApiResponse apiResponse=userService.createUser(userRegisterDto);
		return  ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@GetMapping("/logout")
	public String userLogout() {
		ApiResponse response=new ApiResponse();
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Logout successfully");
		response.setData(null);
		return "logout";
	}
}
