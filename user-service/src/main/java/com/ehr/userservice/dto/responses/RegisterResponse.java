package com.ehr.userservice.dto.responses;

import com.ehr.userservice.enums.UserRole;
import com.ehr.userservice.enums.UserStatus;
import lombok.Builder;

@Builder
public record RegisterResponse(
        String token,
        Long userId,
        String firstname,
        String lastname,
        String username,
        String email,
        String phone,
        UserRole role,
        UserStatus status
) {
}
