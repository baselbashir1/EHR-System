package com.ehr.userservice.dto.responses;

import com.ehr.userservice.enums.UserRole;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
public record UserRoleResponse(
        Long userId,
        UserRole role,
        Collection<? extends GrantedAuthority> getAuthorities
) {
}
