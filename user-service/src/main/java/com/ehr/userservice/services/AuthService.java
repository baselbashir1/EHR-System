package com.ehr.userservice.services;

import com.ehr.userservice.dto.requests.RegisterRequest;
import com.ehr.userservice.dto.responses.AuthResponse;
import com.ehr.userservice.dto.responses.UserResponse;

public interface AuthService {

    UserResponse registerUser(RegisterRequest registerRequest);

    AuthResponse getUserByUsername(String username);

}
