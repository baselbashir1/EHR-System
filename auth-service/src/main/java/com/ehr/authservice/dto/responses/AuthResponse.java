package com.ehr.authservice.dto.responses;

import com.ehr.authservice.enums.UserRole;
import com.ehr.authservice.enums.UserStatus;
import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        Long userId,
        String username,
        String email,
        String phone,
        UserRole role,
        UserStatus status
) {
}
