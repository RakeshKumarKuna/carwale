package com.trisun.config;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{
	
	private final UserAuthenticationProvider userAuthenticationProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String testString="Bearer asddsasdsadfasdfasdcadcasdaszxc";
		String header=request.getHeader(HttpHeaders.AUTHORIZATION);
		if(header!=null) {
			System.out.println("JwtAuthFilter.doFilterInternal() entry 1");
			String[] authElements=header.split(" ");
			
			if(authElements.length == 2 && "Bearer".equals(authElements[0])) {
				System.out.println("JwtAuthFilter.doFilterInternal() entry 2");
                try {
                    if ("GET".equals(request.getMethod())) {
                        SecurityContextHolder.getContext().setAuthentication(
                                userAuthenticationProvider.validateToken(authElements[1]));
                        System.out.println("JwtAuthFilter.doFilterInternal() entry 3");
                    } else {
                        SecurityContextHolder.getContext().setAuthentication(
                                userAuthenticationProvider.validateToken(authElements[1]));
                        System.out.println("JwtAuthFilter.doFilterInternal() entry 4");
                    }
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    System.out.println("inside catch............");
                    e.printStackTrace();
                    throw e;
                }
            }	
		}
		filterChain.doFilter(request, response);
	}

}
