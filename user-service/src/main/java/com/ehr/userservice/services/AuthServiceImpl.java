package com.ehr.userservice.services;

import com.ehr.userservice.dto.requests.LoginRequest;
import com.ehr.userservice.dto.requests.RegisterRequest;
import com.ehr.userservice.dto.responses.AuthResponse;
import com.ehr.userservice.jwt.JwtUtil;
import com.ehr.userservice.mappers.AuthMapper;
import com.ehr.userservice.mappers.UserMapper;
import com.ehr.userservice.models.User;
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
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthMapper authMapper;
    private final UserMapper userMapper;

    public User registerUser(RegisterRequest registerRequest) {
        return userService.save(userMapper.mapToUser(registerRequest));
    }

    // NEW
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticateUser(loginRequest);
        User user = fetchUserDetails(loginRequest.username());

        String token = jwtUtil.generateToken(user.getUsername());
        return authMapper.mapToAuthResponse(token, "Logged in successfully", 200);
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        User registeredUser = registerUser(registerRequest);
        User user = fetchUserDetails(registeredUser.getUsername());

        String token = jwtUtil.generateToken(user.getUsername());
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

    private User fetchUserDetails(String username) {
        User user = userService.loadUserByUsername(username);
        if (user == null) {
            log.error("User not found: {}", username);
            throw new IllegalArgumentException("Username or password incorrect");
        }
        log.info("User details - ID: {}, Username: {}, Role: {}", user.getId(), user.getUsername(), user.getRole());
        return user;
    }

}
