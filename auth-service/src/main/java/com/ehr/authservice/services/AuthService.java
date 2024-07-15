package com.ehr.authservice.services;

import com.ehr.authservice.clients.UserServiceClient;
import com.ehr.authservice.dto.requests.LoginRequest;
import com.ehr.authservice.dto.requests.RegisterRequest;
import com.ehr.authservice.dto.responses.AuthResponse;
import com.ehr.authservice.dto.responses.UserDTO;
import com.ehr.authservice.mappers.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserServiceClient userServiceClient;
    private final JwtService jwtService;
    private final AuthMapper authMapper;

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        if (authenticate.isAuthenticated()) {
            UserDTO userDTO = userServiceClient.getUserByUsername(loginRequest.username()).getBody();
            String token = jwtService.generateToken(loginRequest.username());
            if (userDTO != null) {
                return authMapper.mapToAuthResponse(token, userDTO);
            } else {
                throw new IllegalArgumentException("Username or password incorrect");
            }
        } else {
            throw new IllegalArgumentException("Username or password incorrect");
        }
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        return userServiceClient.save(registerRequest).getBody();
    }

}
