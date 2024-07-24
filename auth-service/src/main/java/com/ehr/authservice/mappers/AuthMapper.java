package com.ehr.authservice.mappers;

import com.ehr.authservice.dto.responses.AuthResponse;
import com.ehr.authservice.dto.responses.UserResponse;

public interface AuthMapper {

    AuthResponse mapToAuthResponse(String token, UserResponse userResponse);

}
