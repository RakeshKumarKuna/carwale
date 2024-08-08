package com.trisun.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IllegalRequesrException extends RuntimeException{
	private String message;
}
