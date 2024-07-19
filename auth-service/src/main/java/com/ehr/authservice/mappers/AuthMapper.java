package com.ehr.authservice.mappers;

import com.ehr.authservice.dto.responses.AuthResponse;
import com.ehr.authservice.dto.responses.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthMapper {

    public AuthResponse mapToAuthResponse(String token, UserResponse userResponse) {
        return AuthResponse.builder()
                .token(token)
                .userId(userResponse.userId())
                .username(userResponse.username())
                .email(userResponse.email())
                .phone(userResponse.phone())
                .role(userResponse.role())
                .status(userResponse.status())
                .build();
    }

}
