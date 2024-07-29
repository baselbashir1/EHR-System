package com.ehr.authservice.mappers;

import com.ehr.authservice.dto.responses.AuthResponse;

public interface AuthMapper {

    AuthResponse mapToAuthResponse(String token, String message, Integer statusCode);

}
