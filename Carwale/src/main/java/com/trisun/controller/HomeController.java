package com.trisun.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/")
public class HomeController {
 
	@GetMapping
	public String welComeHome() {
		
		return "Home Page";
	}
	
	
}
