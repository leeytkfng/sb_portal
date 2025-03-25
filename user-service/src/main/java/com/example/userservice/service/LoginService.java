package com.example.userservice.service;

import com.example.userservice.dto.AccountRequestDto;
import com.example.userservice.dto.AccountResponseDto;
import com.example.userservice.dto.LoginRequestDto;
import com.example.userservice.dto.LoginResponseDto;
import com.example.userservice.entity.Account;
import com.example.userservice.repositroy.AccountRepository;
import com.example.userservice.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public LoginService(AccountRepository accountRepository ,BCryptPasswordEncoder bCryptPasswordEncoder
    ,JwtUtil jwtUtil) {
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDto login(LoginRequestDto request){
        Optional<Account> user = accountRepository.findByEmail(request.getEmail());

        if(user.isEmpty()){
            throw new RuntimeException("사용자 정보가 없습니다.");
        }

        Account account = user.get();

        //비밀번호 검증
        if(!bCryptPasswordEncoder.matches(request.getPassword(),account.getPassword())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.generateToken(account.getEmail());
        System.out.println("토큰발급성공");

        //로그인에 성공하면 응답 DTO 반환
        return new LoginResponseDto(account.getEmail() , account.getPassword() ,token);
    }

    public AccountResponseDto findUserByEmail(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을수없습니다."));

        return new AccountResponseDto(account.getName(), account.getEmail());
    }
}
