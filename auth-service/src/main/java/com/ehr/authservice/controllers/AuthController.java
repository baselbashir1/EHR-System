package com.ehr.authservice.controllers;

import com.ehr.authservice.dto.requests.LoginRequest;
import com.ehr.authservice.dto.requests.RegisterRequest;
import com.ehr.authservice.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;

    //TODO: ADD VALIDATION TO THE REQUESTS
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(authService.register(registerRequest), HttpStatus.OK);
    }

}
