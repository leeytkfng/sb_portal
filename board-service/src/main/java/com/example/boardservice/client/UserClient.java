package com.example.boardservice.client;

import com.example.boardservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service" , url = "http://localhost:8090")
public interface UserClient {

    @GetMapping("/api/auth")
    UserDto getUserByEmail(@RequestParam("email") String email);
}
