package com.ehr.authservice.dto.responses;

import com.ehr.authservice.enums.UserRole;

public record UserDTO(
        Long id,
        String username,
        String password,
        UserRole role
) {
}
