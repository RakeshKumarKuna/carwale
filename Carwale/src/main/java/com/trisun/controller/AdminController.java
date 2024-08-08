//package com.trisun.controller;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.trisun.entity.Brand;
//import com.trisun.entity.Image;
//import com.trisun.entity.dto.CreateBrandDto;
//import com.trisun.response.ApiResponse;
//import com.trisun.service.AdminService;
//
//import lombok.RequiredArgsConstructor;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//
//@CrossOrigin("*")
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/brand")
//public class AdminController {	
//	
//	private final AdminService adminService;
//	
//	
//	@PostMapping("/add-brand")
//	public ResponseEntity<ApiResponse> addBrand(@RequestParam("file") MultipartFile file) throws IOException {	
//		System.out.println("controller entry");
//		ApiResponse apiResponse = adminService.addBrand(file);	
//		apiResponse.setStatus(HttpStatus.OK.value());
//		System.out.println("controller exit");
//		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
//	}
//	
//	@PostMapping("/add-category")
//	public String addCategory(@RequestBody String entity) {		
//		return entity;
//	}
//	
//	 @GetMapping("/{id}")
//	    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
//	        Brand brand = adminService.getImage(id);
//	      byte[] image = brand.getImage();
//	      System.out.println("image");
//	        if (brand != null) {
//	            return ResponseEntity.ok().header("Content-Type", brand.getType()).body(image);
//	        } else {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//	        }
//	    }
//	
//	
//}
