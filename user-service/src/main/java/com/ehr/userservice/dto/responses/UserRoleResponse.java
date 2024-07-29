package com.ehr.userservice.dto.responses;

import com.ehr.userservice.enums.Role;
import lombok.Builder;

@Builder
public record UserRoleResponse(
        Long userId,
        Role role
) {
}
