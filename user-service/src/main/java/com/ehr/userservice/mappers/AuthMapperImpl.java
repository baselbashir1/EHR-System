package com.ehr.userservice.mappers;

import com.ehr.userservice.dto.responses.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthMapperImpl implements AuthMapper {

    @Override
    public AuthResponse mapToAuthResponse(String token, String message, Integer statusCode) {
        return AuthResponse.builder()
                .token(token)
                .message(message)
                .statusCode(statusCode)
                .build();
    }

}
