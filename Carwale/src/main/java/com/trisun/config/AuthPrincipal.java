package com.trisun.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthPrincipal {
 
    private Integer id;
    private String email;
    private String role;
 
}