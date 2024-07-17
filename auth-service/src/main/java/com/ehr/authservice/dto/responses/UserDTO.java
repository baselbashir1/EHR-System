package com.ehr.authservice.dto.responses;

import com.ehr.authservice.enums.UserRole;
import lombok.Builder;

@Builder
public record UserDTO(
        Long userId,
        String username,
        String password,
        UserRole role
) {
}
