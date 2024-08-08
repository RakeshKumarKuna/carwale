package com.trisun.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
 
import jakarta.servlet.ServletException;
 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
 
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        String expiredMsg = (String) request.getAttribute("expired");
        String invalidMsg = (String) request.getAttribute("invalid");
        if (expiredMsg != null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, expiredMsg);
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, expiredMsg);
        } else if (invalidMsg != null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, invalidMsg);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}
