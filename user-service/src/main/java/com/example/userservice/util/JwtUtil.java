package com.example.userservice.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "your-secret-key-your-secret-key-your-secret-key";
    private static final long EXPIRATION_TIME = 1000 *60 *60; //1시간

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); //바이트 형식으로 해싱

    //jwt 생성
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email) //토큰 주체
                .setIssuedAt(new Date()) //발행 시칸
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //만료시간
                .signWith(key, SignatureAlgorithm.HS256) //HS256 알고리즘으로 키값 생성
                .compact();
    }

    // JWT 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    //JWT에서 이메일 추출 -> 저장
    public String getEmailFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); //이메일 추출
    }
}
