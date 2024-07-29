package com.ehr.authservice.dto.responses;

import lombok.Builder;

@Builder
public record UserResponse(
        Long userId,
        String username,
        String email,
        String phone,
        String password,
        String role,
        String status
) {
}
