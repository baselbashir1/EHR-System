package com.ehr.authservice.clients;

import com.ehr.authservice.dto.requests.RegisterRequest;
import com.ehr.authservice.dto.responses.AuthResponse;
import com.ehr.authservice.dto.responses.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserServiceClient {

    @PostMapping("/register")
    ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest);

    @GetMapping("/getUserByUsername/{username}")
    ResponseEntity<UserResponse> getUserByUsername(@PathVariable("username") String username);

}