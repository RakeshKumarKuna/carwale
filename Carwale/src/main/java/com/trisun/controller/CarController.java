package com.trisun.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trisun.config.AuthPrincipal;
import com.trisun.entity.dto.CarDto;
import com.trisun.response.ApiResponse;
import com.trisun.service.CarService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RequiredArgsConstructor
@CrossOrigin( "*")
@RestController
@RequestMapping("/cars")
public class CarController {

	private final CarService carService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> addCar( @RequestBody CarDto carDto) {
		System.out.println("add car controller ");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
		System.out.println("authentication+++++++ "+authentication);
		if (authentication != null) {
            AuthPrincipal principal = (AuthPrincipal) authentication.getPrincipal();
         Integer id = principal.getId();
           String email = principal.getEmail();
           System.out.println("+++++++++ Id +++++"+id+"========email "+email);
            try {
            	 ApiResponse response = this.carService.addCar(carDto, 9); 
                 System.out.println("api response ===="+response);
                 return ResponseEntity.status(response.getStatus()).body(response);
			} catch (Exception e) {
				   System.out.println("ctach bolck  ========== "+e);
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(null);
			}
           
        } else {
        	
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(null);
        }		
	}
	
	@GetMapping("/limited")
	public ResponseEntity<ApiResponse> getLimitedCars(@RequestParam Integer limit ) {
		ApiResponse apiResponse = carService.getLimitedCars(limit);	
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse> getAllCars( ) {
		ApiResponse apiResponse = carService.getAllCars();	
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getMethodName(@PathVariable Integer id) {
		ApiResponse apiResponse = carService.getCarById(id);		
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	
	@PutMapping("/update-car")
	public String putMethodName(@PathVariable String id, @RequestBody String entity) {
		return entity;
	}
	
}
