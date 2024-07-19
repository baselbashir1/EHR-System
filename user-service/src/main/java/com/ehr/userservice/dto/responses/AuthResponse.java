package com.ehr.userservice.dto.responses;

import com.ehr.userservice.enums.UserRole;
import com.ehr.userservice.enums.UserStatus;
import lombok.Builder;

@Builder
public record AuthResponse(
        Long userId,
        String username,
        String email,
        String phone,
        String password,
        UserRole role,
        UserStatus status
) {
}