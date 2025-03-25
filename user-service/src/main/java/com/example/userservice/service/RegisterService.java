package com.example.userservice.service;

import com.example.userservice.dto.AccountRequestDto;
import com.example.userservice.dto.AccountResponseDto;
import com.example.userservice.entity.Account;
import com.example.userservice.repositroy.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class RegisterService {

    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterService(AccountRepository accountRepository ,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public AccountResponseDto register(AccountRequestDto request){
        Account account = Account.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();

        Account save  = accountRepository.save(account);

        return new AccountResponseDto(save.getName(),save.getEmail());

    }
}
