package com.ehr.userservice.services;

import com.ehr.userservice.dto.responses.UserRoleResponse;
import com.ehr.userservice.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityLayerServiceImpl implements SecurityLayerService {

    private final UserService userService;

    @Override
    public UserRoleResponse getUserIdAndRoleFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.loadUserByUsername(username);
        return new UserRoleResponse(user.getId(), user.getRole());
    }

}
