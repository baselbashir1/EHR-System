package com.ehr.userservice.mappers;

import com.ehr.userservice.dto.responses.AuthResponse;

public interface AuthMapper {

    AuthResponse mapToAuthResponse(String token, String message, Integer statusCode);

}
