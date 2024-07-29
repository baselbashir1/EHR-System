package com.ehr.userservice.dto.responses;

import com.ehr.userservice.enums.Role;
import com.ehr.userservice.enums.Status;
import lombok.Builder;

@Builder
public record UserResponse(
        Long userId,
        String firstname,
        String lastname,
        String username,
        String email,
        String phone,
        Role role,
        Status status
) {
}
