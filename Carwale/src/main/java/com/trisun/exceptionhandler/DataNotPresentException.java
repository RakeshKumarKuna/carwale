package com.trisun.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DataNotPresentException extends RuntimeException{

private String message;
}

