package com.ehr.authservice.services;

import com.ehr.authservice.clients.UserServiceClient;
import com.ehr.authservice.dto.requests.LoginRequest;
import com.ehr.authservice.dto.requests.RegisterRequest;
import com.ehr.authservice.dto.responses.AuthResponse;
import com.ehr.authservice.dto.responses.UserResponse;
import com.ehr.authservice.mappers.AuthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserServiceClient userServiceClient;
    private final JwtService jwtService;
    private final AuthMapper authMapper;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticateUser(loginRequest);
        UserResponse userResponse = fetchUserDetails(loginRequest.username());

        String token = jwtService.generateToken(userResponse.username());
        return authMapper.mapToAuthResponse(token, "Logged in successfully", 200);
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        UserResponse registeredUser = userServiceClient.register(registerRequest).getBody();
        UserResponse userResponse = fetchUserDetails(registeredUser.username());

        String token = jwtService.generateToken(userResponse.username());
        return authMapper.mapToAuthResponse(token, "Registered successfully", 201);
    }

    private void authenticateUser(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        } catch (Exception e) {
            log.error("Authentication failed for user: {}", loginRequest.username(), e);
            throw new IllegalArgumentException("Username or password incorrect");
        }
    }

    private UserResponse fetchUserDetails(String username) {
        UserResponse userResponse = userServiceClient.getUserByUsername(username).getBody();
        if (userResponse == null) {
            log.error("User not found: {}", username);
            throw new IllegalArgumentException("Username or password incorrect");
        }
        log.info("User details - ID: {}, Username: {}, Role: {}", userResponse.userId(), userResponse.username(), userResponse.role());
        return userResponse;
    }

}