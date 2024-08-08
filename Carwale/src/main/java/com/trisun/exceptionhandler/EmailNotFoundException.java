package com.trisun.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EmailNotFoundException extends RuntimeException {
	private String message;
}
