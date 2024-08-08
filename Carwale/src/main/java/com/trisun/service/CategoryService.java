package com.trisun.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trisun.entity.Category;
import com.trisun.entity.Image;
import com.trisun.entity.dto.CategoryDto;
import com.trisun.entity.dto.CreateCarDto;
import com.trisun.repository.CategoryRepository;
import com.trisun.repository.ImageRepository;
import com.trisun.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final ImageRepository imageRepository;
	private final CategoryRepository categoryRepository;
	
	public ApiResponse createCategory(CategoryDto categoryDto) {
		ApiResponse apiResponse = new ApiResponse();
		Image image = new Image();
		image.setUrl(categoryDto.getImageUrl());
		Image savedImage = imageRepository.save(image);
		Category category = new Category();
		category.setName(categoryDto.getName());		
		category.setIsFeatured(categoryDto.getIsFeatured());
		category.setImage(savedImage);
		Category savedCategory = categoryRepository.save(category);
		apiResponse.success(savedCategory);
		return apiResponse;
	}
	
	
	public List<Category> getAllCategories(){		
		List<Category> allCategories = categoryRepository.findAll();		
		return allCategories;
	}
	
	public ApiResponse deleteCategory(Integer id) {	
		Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("category not found with id"));	
		categoryRepository.deleteById(category.getId());
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("category deleted succesfully");
		return apiResponse;
	}
	
	public ApiResponse updateCategory(Integer id,CategoryDto categoryDto  ) {
		 ApiResponse apiResponse = new ApiResponse();
		 Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("category not foud with id"));
		 category.setName(categoryDto.getName());
		 category.setIsFeatured(categoryDto.getIsFeatured());
		 Category updatedcategory = categoryRepository.save(category);
		 apiResponse.success(updatedcategory);
		return apiResponse;
	}
	
	public ApiResponse getCategoryById(Integer id) {
		Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found with id"));
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.success(category);
		return apiResponse;
	}
	
	
}
