package com.ehr.userservice.mappers;

import com.ehr.userservice.dto.responses.AuthResponse;
import com.ehr.userservice.models.User;

public interface AuthMapper {

    AuthResponse mapToAuthResponse(User user);

}
