package com.ehr.userservice.services;

import com.ehr.userservice.dto.requests.LoginRequest;
import com.ehr.userservice.dto.requests.RegisterRequest;
import com.ehr.userservice.dto.responses.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);

}
