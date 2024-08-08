package com.trisun.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trisun.response.ApiResponse;
import com.trisun.service.ImagesService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class ImageController {

	private final ImagesService imagesService;
	
	@PostMapping("/image-upload")
    public ResponseEntity<ApiResponse> createImage(@RequestParam("image") MultipartFile imageFile) {
        ApiResponse response = imagesService.saveImage(imageFile);
 
        return ResponseEntity.status(response.getStatus()).body(response);
    }
 
	
}
