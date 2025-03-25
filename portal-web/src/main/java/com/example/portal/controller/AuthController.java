package com.example.portal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final StringRedisTemplate redisTemplate;

    public AuthController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(name= "jwt", required = false)String token , HttpServletResponse response) {
        if(token != null && !token.isEmpty()) {
            redisTemplate.delete(token);
            Cookie deleteCookie = new Cookie("jwt", null);
            deleteCookie.setPath("/");
            deleteCookie.setMaxAge(0);
            response.addCookie(deleteCookie);
        }

        return ResponseEntity.ok("로그아웃 성공!");
    }
}
