package com.trisun.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trisun.entity.User;
import com.trisun.entity.dto.UserAuthDto;
import com.trisun.response.ApiResponse;
import com.trisun.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	@GetMapping
	public String getUsers() {
		return "All users";
	}
	
	@PostMapping()
	public ResponseEntity<ApiResponse> createUser(@RequestBody @Valid UserAuthDto userRegisterDto) {		
		ApiResponse apiResponse = userService.createUser(userRegisterDto);	
		return new ResponseEntity<ApiResponse>(HttpStatus.OK);
	}

}
