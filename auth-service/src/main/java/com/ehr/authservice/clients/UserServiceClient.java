package com.ehr.authservice.clients;

import com.ehr.authservice.dto.requests.RegisterRequest;
import com.ehr.authservice.dto.responses.AuthResponse;
import com.ehr.authservice.dto.responses.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserServiceClient {

    @PostMapping("/save")
    ResponseEntity<AuthResponse> save(@RequestBody RegisterRequest registerRequest);

    @GetMapping("/getUserByUsername/{username}")
    ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username);
}