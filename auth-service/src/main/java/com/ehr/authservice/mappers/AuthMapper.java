package com.ehr.authservice.mappers;

import com.ehr.authservice.dto.responses.AuthResponse;
import com.ehr.authservice.dto.responses.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthMapper {

    public AuthResponse mapToAuthResponse(String token, UserDTO userDTO) {
        return AuthResponse.builder()
                .token(token)
                .userId(userDTO.userId())
                .username(userDTO.username())
                .role(userDTO.role())
                .build();
    }

}
