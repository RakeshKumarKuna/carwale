package com.trisun.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trisun.entity.Category;
import com.trisun.entity.dto.CategoryDto;
import com.trisun.response.ApiResponse;
import com.trisun.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

	private final CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryDto categoryDto){
		ApiResponse apiResponse = categoryService.createCategory(categoryDto);		
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@GetMapping
	public List<Category> getAllcategories(){
		List<Category> allCategories = categoryService.getAllCategories();
		return allCategories;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
		ApiResponse apiResponse = categoryService.deleteCategory(id);
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
		
	   ApiResponse apiResponse = categoryService.updateCategory(id, categoryDto);
	  	
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getMethodName(@PathVariable Integer id) {
		ApiResponse apiResponse = categoryService.getCategoryById(id);
		
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
  
}
