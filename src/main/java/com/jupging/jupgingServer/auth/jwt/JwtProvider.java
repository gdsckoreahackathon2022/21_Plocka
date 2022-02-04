package com.jupging.jupgingServer.auth.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    private final Key key;

    private final static Long ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 * 1000L;
    private final static Long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;

    public JwtProvider(@Value("${spring.jwt.secret}") String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String userId) {
        Date now = new Date();
        Claims claims = Jwts.claims().setSubject(userId);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();
    }

    public String getUserId(String token) {
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(getUserId(token), "",
            Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer")) {
            return header.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 형식의 Jwt 토큰입니다.", e);
        } catch (ExpiredJwtException e) {
            log.info("만료된 Jwt 토큰입니다.", e);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 Jwt 토큰입니다.", e);
        } catch (IllegalArgumentException e) {
            log.info("잘못된 헤더의 Jwt 토큰입니다.", e);
        }
        return false;
    }

    public boolean validationToken(String token) {
        return parseClaims(token)
            .getExpiration()
            .after(new Date());
    }

}
