package com.example.dncinema.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author ChinhLV
 * @Param request
 * @Param response
 * @Param authException
 * @void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
 * Phương thức sử dụng để log ra lỗi nếu truy cập vào nguồn tài nguyên không được cấp quyền
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error Message {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_ACCEPTED, "Error -> Unauthorized");
    }
}
