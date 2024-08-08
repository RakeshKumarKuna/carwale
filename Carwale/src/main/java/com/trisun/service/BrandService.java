package com.trisun.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.trisun.entity.Brand;
import com.trisun.entity.Image;
import com.trisun.entity.dto.CreateBrandDto;
import com.trisun.repository.BrandRepository;
import com.trisun.repository.ImageRepository;
import com.trisun.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequestMapping("/brands")
@RequiredArgsConstructor
@Service
public class BrandService {

	private final BrandRepository brandRepository;
	private final ImageRepository imageRepository;
	
	 @Transactional
	public ApiResponse createBrand(CreateBrandDto createBrandDto) throws IOException {

		ApiResponse apiResponse = new ApiResponse();

	        Image image = new Image();
	        image.setUrl(createBrandDto.getImageUrl());
            Image savedImage = imageRepository.save(image);
            System.out.println(savedImage);
            System.out.println("inside create brand 4");
	        Brand brand = new Brand();
	        brand.setName(createBrandDto.getBrandName());
	        brand.setIsFeatured(createBrandDto.getIsFeatured());
	        brand.setImage(savedImage);	     
	        Brand savedBrand = brandRepository.save(brand);
	        System.out.println("inside create brand 5"+savedBrand);
	        apiResponse.success(savedBrand);
	        return apiResponse;
	}
	 	
	 	
	public Brand getBrand(Integer id) {
		 brandRepository.findById(id);	    
		return brandRepository.findById(id).orElseThrow(()-> new RuntimeException("brand nor found"));
	}
	
   public ApiResponse getAllBrands(){
	   
	   ApiResponse apiResponse=new ApiResponse();
	   List<Brand> allBrands = brandRepository.findAll();
	  apiResponse.success(allBrands);
	  System.out.println(apiResponse);
	
	   return apiResponse;
   }

	public Image getImage(Integer id) {
		Image image = new Image();
		image.getUrl();
		return imageRepository.findById(id).orElseThrow(()-> new RuntimeException("image nor found"));
	}
	
	public ApiResponse updateBrand(Integer id,CreateBrandDto createBrandDto) {
		
		 Brand brand = brandRepository.findById(id).orElseThrow(()-> new
		 RuntimeException("Brand not found")); 
		 brand.setName(createBrandDto.getBrandName()); 
		 brand.setIsFeatured(createBrandDto.getIsFeatured());		
     	 Brand savedBrand = brandRepository.save(brand); 
     	 ApiResponse apiResponse = new ApiResponse();   	 
     	 apiResponse.success(savedBrand);
		 return apiResponse;
		 
//		return brandRepository.findById(id).map(brand -> {Brand updateBrand= mapToBrand(createBrandDto);
//				updateBrand.setId(brand.getId());
//				brandRepository.save(updateBrand);
//				return new ApiResponse().success(updateBrand);
//				}).orElseThrow(() -> new RuntimeException("failed to update"));
	}
	
	public Brand mapToBrand(CreateBrandDto createBrandDto) {
		Brand brand = new Brand();
		brand.setName(createBrandDto.getBrandName());
		brand.setIsFeatured(createBrandDto.getIsFeatured());
		Image image = new Image();
		//imageRepository.findByUrl(createBrandDto.getImageUrl());
		brand.setImage(image);
		return brand;
	}
	
	public String deleteBrand(Integer id) {
		Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
		brandRepository.deleteById(brand.getId());
		return "deleted successfully";
	}
}
