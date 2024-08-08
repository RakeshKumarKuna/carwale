package com.trisun.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.trisun.entity.Brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse  {

	private Integer status;
	private String message;
	private Object data;
	
	public ApiResponse success(Object data) {
        this.setStatus(HttpStatus.OK.value());
        this.setMessage("Success");
        this.setData(data);
        return this;
    }
 
      public ApiResponse error(int status, String message) {
        this.setStatus(status);
        this.setMessage(message);
        this.setData(null); // Or set data to null/error message (optional)
        return this;
    }
}
