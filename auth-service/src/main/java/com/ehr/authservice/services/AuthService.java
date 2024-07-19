package com.ehr.authservice.services;

import com.ehr.authservice.clients.UserServiceClient;
import com.ehr.authservice.dto.requests.LoginRequest;
import com.ehr.authservice.dto.requests.RegisterRequest;
import com.ehr.authservice.dto.responses.AuthResponse;
import com.ehr.authservice.dto.responses.UserDTO;
import com.ehr.authservice.mappers.AuthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserServiceClient userServiceClient;
    private final JwtService jwtService;
    private final AuthMapper authMapper;

    public AuthResponse login(LoginRequest loginRequest) {
        authenticateUser(loginRequest);
        UserDTO userDTO = fetchUserDetails(loginRequest.username());

        String token = jwtService.generateToken(userDTO.username());
        return authMapper.mapToAuthResponse(token, userDTO);
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        AuthResponse registeredUser = userServiceClient.save(registerRequest).getBody();
        UserDTO userDTO = fetchUserDetails(registeredUser.username());

        String token = jwtService.generateToken(userDTO.username());
        return authMapper.mapToAuthResponse(token, userDTO);
    }

    private void authenticateUser(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        } catch (Exception e) {
            log.error("Authentication failed for user: {}", loginRequest.username(), e);
            throw new IllegalArgumentException("Username or password incorrect");
        }
    }

    private UserDTO fetchUserDetails(String username) {
        UserDTO userDTO = userServiceClient.getUserByUsername(username).getBody();
        if (userDTO == null) {
            log.error("User not found: {}", username);
            throw new IllegalArgumentException("Username or password incorrect");
        }
        log.info("User details - ID: {}, Username: {}, Role: {}", userDTO.userId(), userDTO.username(), userDTO.role());
        return userDTO;
    }

}