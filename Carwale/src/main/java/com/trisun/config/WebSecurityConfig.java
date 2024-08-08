package com.trisun.config;

import java.net.http.HttpHeaders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.trisun.core.UserRoles;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private final UserAuthenticationProvider userAuthenticationProvider;
	private final String[] publicUrls = { "/", "/uploads/**", "/login","/register","/add-brand","/image-upload","/cars/**"};
	private final String[] adminUrls = { "/brands/**","/categories/**" };
	private final String[] publicGetUrls= {"/brands/**","/categories/**"};
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	   
		System.out.println("WebSecurityConfig.securityFilterChain() entry 1");
		httpSecurity
	    .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
	   
	    .exceptionHandling(exeption -> exeption.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
	     
	    .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        System.out.println("entry 2");
		httpSecurity.authorizeHttpRequests(auth -> auth
				
             .requestMatchers(publicUrls).permitAll()
             .requestMatchers(HttpMethod.PUT, adminUrls).hasAuthority("Admin")
             .requestMatchers(HttpMethod.POST, adminUrls).hasAuthority("Admin")
             .requestMatchers(HttpMethod.DELETE, adminUrls).permitAll()
//           .requestMatchers("/image-upload").hasAuthority(UserRoles.ADMIN)
             .requestMatchers(HttpMethod.GET,publicGetUrls).permitAll()
             .anyRequest().authenticated());
		
		System.out.println("entry 3");
		httpSecurity.csrf(csrf->csrf.disable());
		
		return httpSecurity.build();
	}	
	
	
}



