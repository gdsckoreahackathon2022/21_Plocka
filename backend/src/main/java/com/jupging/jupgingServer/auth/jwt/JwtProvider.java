package com.jupging.jupgingServer.auth.jwt;

import com.jupging.jupgingServer.common.BaseException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import static com.jupging.jupgingServer.common.BaseResponseStatus.EMPTY_JWT;
import static com.jupging.jupgingServer.common.BaseResponseStatus.INVALID_JWT;

@Slf4j
@Component
public class JwtProvider {

    private String secretKey;

    public JwtProvider(@Value("${spring.jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    // 로그인 (이메일 )
    // DB에서 찾고
    //있으면 isNew false 원래있던 userId => token
    //없으면 만들고 만든 userId => token

    /*
     JWT 생성
     @param userIdx
     @return String
      */
    public String createJwt(Long userIdx) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("userIdx", userIdx)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 60 * 24 * 365)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /*
    Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    @return String
     */
    public String getJwt() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    /*
    JWT에서 userIdx 추출
    @return int
    @throws BaseException
     */
    public Long getUserIdx() throws BaseException {
        //1. JWT 추출
        String accessToken = getJwt();
        if (accessToken == null || accessToken.length() == 0) {
            throw new BaseException(EMPTY_JWT);
        }

        // 2. JWT parsing
        Jws<Claims> claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(accessToken);
        } catch (Exception ignored) {
            throw new BaseException(INVALID_JWT);
        }

        // 3. userIdx 추출
        return claims.getBody().get("userIdx", Long.class);  // jwt 에서 userIdx를 추출합니다.
    }
}
//    private String secretKey;
//
//    private final static Long ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 * 1000L;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    public JwtProvider(@Value("${spring.jwt.secret}") String secretKey) {
//        this.secretKey = secretKey;
//    }
//
//    @PostConstruct
//    private void init() {
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//    }
//
//    public String createToken(String userId) {
//        Date now = new Date();
//        Claims claims = Jwts.claims().setSubject(userId);
//
//        return Jwts.builder()
//            .setClaims(claims)
//            .setIssuedAt(now)
//            .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
//            .signWith(SignatureAlgorithm.HS256, secretKey)
//            .compact();
//    }
//
//    public String getUserId(String token) {
//        return parseClaims(token).getSubject();
//    }
//
//    public Claims parseClaims(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//    }
//
//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
//        return new UsernamePasswordAuthenticationToken(getUserId(token), "", userDetails.getAuthorities());
//    }
//
//    public String resolveToken(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        if (StringUtils.hasText(header) && header.startsWith("Bearer")) {
//            return header.substring("Bearer ".length());
//        }
//        return null;
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            return true;
//        } catch (MalformedJwtException e) {
//            log.info("잘못된 형식의 Jwt 토큰입니다.", e);
//        } catch (ExpiredJwtException e) {
//            log.info("만료된 Jwt 토큰입니다.", e);
//        } catch (UnsupportedJwtException e) {
//            log.info("지원되지 않는 Jwt 토큰입니다.", e);
//        } catch (IllegalArgumentException e) {
//            log.info("잘못된 헤더의 Jwt 토큰입니다.", e);
//        }
//        return false;
//    }
//
//    public boolean validationToken(String token) {
//        try {
//            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            return !claims.getBody().getExpiration().before(new Date());
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//}
