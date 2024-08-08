//package com.trisun.config;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Component
//public class WebConfig implements WebMvcConfigurer {
//   @Override
//   public void addCorsMappings(CorsRegistry registry) {
//       registry.addMapping("/**")
//               .allowedOrigins("http://localhost:4200")
//               .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
//               .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization", "Cookie")
//               .exposedHeaders(HttpHeaders.SET_COOKIE)
//               .allowCredentials(true);
//   }
//
//}