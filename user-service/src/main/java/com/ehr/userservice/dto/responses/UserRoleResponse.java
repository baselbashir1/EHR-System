package com.ehr.userservice.dto.responses;

import com.ehr.userservice.enums.UserRole;
import lombok.Builder;

@Builder
public record UserRoleResponse(
        Long userId,
        UserRole role
) {
}
