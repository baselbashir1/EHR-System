package com.ehr.userservice.dto.responses;

import com.ehr.userservice.enums.Role;
import com.ehr.userservice.enums.Status;
import lombok.Builder;

@Builder
public record AuthResponse(
        Long userId,
        String username,
        String email,
        String phone,
        String password,
        Role role,
        Status status
) {
}