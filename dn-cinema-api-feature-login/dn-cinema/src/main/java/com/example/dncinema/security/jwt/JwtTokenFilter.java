package com.example.dncinema.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author ChinhLV
 * @Param request
 * @Param response
 * @Param filterChain
 * @return void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
 * Phương thức sử dụng để kiểm tra token có hợp lệ hay không trước khi đến trang đích
 *
 * @Param request
 * @return getJwt(HttpServletRequest request)
 * Phương thức sử dụng để lấy token từ request gửi đi
 * Kết quả là 1 chuỗi token được lấy từ Authorization Header trong request gửi đi
 *
 */
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getJwt(request);
            if (StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
                String username = jwtProvider.getUserNameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (userDetails == null) {
                    response.setStatus(HttpStatus.NOT_FOUND.value());
                }
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }  catch (Exception e) {
            logger.error("Can't set user authentication -> Message: {}", e);
        }
        filterChain.doFilter(request, response);
    }
    private String getJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
