package com.ehr.authservice.dto.responses;

import com.ehr.authservice.enums.UserRole;
import com.ehr.authservice.enums.UserStatus;
import lombok.Builder;

@Builder
public record UserResponse(
        Long userId,
        String username,
        String email,
        String phone,
        String password,
        UserRole role,
        UserStatus status
) {
}
