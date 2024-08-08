package com.trisun.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trisun.entity.Brand;
import com.trisun.entity.Image;
import com.trisun.entity.dto.CreateBrandDto;
import com.trisun.response.ApiResponse;
import com.trisun.service.BrandService;

import lombok.Delegate;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandController {

	private final BrandService brandService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> addBrand(@RequestBody CreateBrandDto createBrandDto ) throws IOException {	  
		System.out.println("inside add brand controller");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("authentication "+authentication);
		ApiResponse apiResponse = brandService.createBrand(createBrandDto);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse) ;
	}
	
	@GetMapping("/{id}")
	public Brand getBrand(@PathVariable Integer id) {
		Brand brand = brandService.getBrand(id);
		return brand;
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse> getAllBrands(){
		System.out.println("BrandController.getAllBrands() entry");
	
	    ApiResponse apiResponse = brandService.getAllBrands();		
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateBrand(@PathVariable Integer id,@RequestBody CreateBrandDto createBrandDto) {	
		
		 ApiResponse apiResponse = brandService.updateBrand(id, createBrandDto);
		 return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse) ;
		
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<ApiResponse> deleteBrand(@PathVariable Integer id) {
		String deleteBrand = brandService.deleteBrand(id);	
		ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage(deleteBrand);		
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);				
	}
	
	
	
   
}
