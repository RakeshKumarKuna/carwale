//package com.trisun.service;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.trisun.entity.Brand;
//import com.trisun.entity.Image;
//import com.trisun.entity.dto.CreateBrandDto;
//import com.trisun.repository.BrandRepository;
//import com.trisun.response.ApiResponse;
//
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@Service
//public class AdminService {
//
//	private final BrandRepository brandRepository;
//
//	public ApiResponse addBrand(MultipartFile file) throws IOException {
//		System.out.println("inside add brand method");
//		ApiResponse apiResponse = new ApiResponse();
//		try {
//			System.out.println("inside add brand method inside try ");
//			Brand brand = new Brand();
//			brand.setName(file.getOriginalFilename());
//			brand.setType(file.getContentType());
//			brand.setImage(file.getBytes());
//			Brand savedBrand = brandRepository.save(brand);
//			System.out.println(" brand data saved  ");
//			apiResponse.success(savedBrand);		
//			return apiResponse;
//		} catch (Exception e) {
//			return apiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error occured ");
//			
//		}
//		
//	}
//	
//	  public Brand getImage(Integer id) {
//	        return brandRepository.findById(id).orElse(null);
//	    }
//}
