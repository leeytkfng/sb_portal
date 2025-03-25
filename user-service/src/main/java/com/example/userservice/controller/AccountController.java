package com.example.userservice.controller;

import com.example.userservice.dto.AccountRequestDto;
import com.example.userservice.dto.AccountResponseDto;
import com.example.userservice.dto.LoginRequestDto;
import com.example.userservice.dto.LoginResponseDto;
import com.example.userservice.service.LoginService;
import com.example.userservice.service.RegisterService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AccountController {

    private final RegisterService registerService;

    private final LoginService loginService;

    private final StringRedisTemplate redisTemplate;

    public AccountController(RegisterService registerService , LoginService loginService, StringRedisTemplate template){
        this.registerService= registerService;
        this.loginService = loginService;
        this.redisTemplate = template;
    }

    @PostMapping("/register")
    public ResponseEntity<AccountResponseDto> register(@RequestBody AccountRequestDto request) {
        AccountResponseDto response = registerService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> Login(@RequestBody LoginRequestDto request ){
       LoginResponseDto response = loginService.login(request);

       //Redis에 토큰을 저장하라 (key: jwt 토큰/ value:email)
       redisTemplate.opsForValue().set(response.getToken(), response.getEmail());

       return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<AccountResponseDto> getUserByEmail(@RequestParam String email){
        //예시: 이메일로 사용자 정보 조회
        AccountResponseDto responseDto = loginService.findUserByEmail(email);
        return ResponseEntity.ok(responseDto);
    }
}
