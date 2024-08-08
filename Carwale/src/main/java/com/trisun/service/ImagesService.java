package com.trisun.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trisun.core.AppConstants;
import com.trisun.response.ApiResponse;

@Service
public class ImagesService {

	
	
	public ApiResponse saveImage(MultipartFile imageFile) {
        ApiResponse response = new ApiResponse();
        Path currentPath = Paths.get("");
        String absolutePath = currentPath.toAbsolutePath().toString();
        String UPLOAD_DIR = absolutePath + "/src/main/resources/static/uploads";
 
        try {
            String randomString = UUID.randomUUID().toString();
            String fileExtension = getFileExtension(imageFile);
            String originalFilename = imageFile.getOriginalFilename();
            String fileNameWithoutExt = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            String fileName = fileNameWithoutExt + "_" + randomString + "." + fileExtension;
            // File destinationFile = new File(UPLOAD_DIR + "/" + fileName);
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            File uploadedFile = new File(uploadDir + "/" + fileName);
            imageFile.transferTo(uploadedFile);
            Map<String, String> data = new HashMap<>();
            String path = AppConstants.baseApiUrl + "/" + AppConstants.uploadsPublicDirectory;
            data.put("path", path);
            data.put("fileName", fileName);
            data.put("imageUrl", path + "/" + fileName);
//          data.put("imageUrl", fileName);
            response.success(data);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Try later");
            return response;
        }
    }
 
    private String getFileExtension(MultipartFile image) {
        String fileName = image.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
	
	
}
