package com.ehr.userservice.mappers;

import com.ehr.userservice.dto.responses.AuthResponse;
import com.ehr.userservice.models.User;
import org.springframework.stereotype.Service;

@Service
public class AuthMapper {

    public AuthResponse mapToAuthResponse(User user) {
        return AuthResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }

}
