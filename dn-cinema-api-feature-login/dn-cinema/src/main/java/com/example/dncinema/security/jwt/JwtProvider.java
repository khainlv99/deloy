package com.example.dncinema.security.jwt;

import com.example.dncinema.security.userPrincipal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 * @author ChinhLV
 * @Param authentication
 * @return String createToken(Authentication authentication)
 * Phương thức sử dụng để tạo ra token dựa vào tham số đầu vào là 1 đối tượng của Authentication
 * Kết quả trả về là 1 chuỗi token đã tạo
 *
 * @Param token
 * @return boolean validateToken(String token)
 * Phương thức sử dụng check token hợp lệ hay không
 * Nếu tên token hợp lệ thì trả về true, ngược lại false.
 *
 * @Param token
 * @return String getUserNameFromToken(String token)
 * Phương thức sử dụng để lấy tên của AccountUser từ token
 */
@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "chinh_fpt";
    private long jwtExpiration = 80640000L;
    public String createToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {}", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid format token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupport JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;
    }
    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
