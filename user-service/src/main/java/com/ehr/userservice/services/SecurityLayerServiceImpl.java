package com.ehr.userservice.services;

import com.ehr.userservice.dto.responses.UserRoleResponse;
import com.ehr.userservice.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityLayerServiceImpl implements SecurityLayerService {

    private final UserService userService;

    @Override
    public UserRoleResponse getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.loadUserByUsername(username);
        return new UserRoleResponse(user.getId(), user.getRole(), user.getAuthorities());
    }

    @Override
    public void checkRole(String role) {
        UserRoleResponse userRoleResponse = getUserDetails();
        String userRole = userRoleResponse.role().name();
        log.info("Checking role {} for user {}", role, userRole);

        if (!userRole.equals(role)) {
            throw new AccessDeniedException("You don't have permission to perform this action");
        }
    }

    @Override
    public void checkAuthorities(List<String> authorities) {
        UserRoleResponse userRoleResponse = getUserDetails();
        Set<String> userAuthorities = userRoleResponse.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        log.info("Checking authorities {} for user {}", authorities, userRoleResponse.role());
        Set<String> missingAuthorities = new HashSet<>(authorities);
        missingAuthorities.removeAll(userAuthorities);
        log.info("Missing authorities: {}", missingAuthorities);

        if (!missingAuthorities.isEmpty()) {
            throw new AccessDeniedException("You don't have permission to perform this action");
        }
    }

}
