package com.trisun.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.trisun.entity.dto.UserAuthDto;
@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider  {
	
   @Value("${secretkey.jwt}")
   private	String secretKey;
 
	public Authentication validateToken(String token) {
		System.out.println("validate token");
         try {
        	 System.out.println("try");
        	 Algorithm algorithm = Algorithm.HMAC256(secretKey);
			 
 	        JWTVerifier verifier = JWT.require(algorithm).build();
 	 
 	        DecodedJWT decoded = verifier.verify(token);	
 	        
 	       AuthPrincipal authPrincipal = new AuthPrincipal();
           authPrincipal.setEmail(decoded.getClaim("email").asString());
           authPrincipal.setId(decoded.getClaim("id").asInt());
           authPrincipal.setRole(decoded.getClaim("role").asString());
 	        
           
 	        UserAuthDto userAuthDto = new UserAuthDto();
 	        userAuthDto.setEmail(decoded.getSubject());
 	        System.out.println("validate token after userAuthDto");
       //   UserDetails userDetails = customUserDetailsService.loadUserByUsername(userAuthDto.getEmail());
 	        String role=decoded.getClaim("role").asString();
 	        System.out.println("role---------"+role);
 	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
 	        authorities.add(new SimpleGrantedAuthority(role));
 	        System.out.println("authorities  "+authorities);
 	   
 	        userAuthDto.setUserRole(role);
 	        System.out.println("validate token last userAuthDto");
 	        
 	        return new UsernamePasswordAuthenticationToken(authPrincipal,null, authorities);
		} catch (Exception e) {
			 System.out.println("inside catch");
			throw e;
		}		
	
	}
	
	public String createToken(int id,String email,String role) {
		
		Date now = new Date();
        Date validity = new Date(now.getTime() + 36000000); // 1 hour
 
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String token=JWT.create()
                .withSubject(email)
                .withIssuedAt(now)
                .withExpiresAt(validity)   
                .withClaim("id",id)
                .withClaim("email", email)
                .withClaim("role",role )
                .sign(algorithm);
		return token;
	}


	
	
}
