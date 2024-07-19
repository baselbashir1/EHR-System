package com.ehr.userservice.mappers;

import com.ehr.userservice.dto.requests.RegisterRequest;
import com.ehr.userservice.dto.responses.UserResponse;
import com.ehr.userservice.enums.UserRole;
import com.ehr.userservice.enums.UserStatus;
import com.ehr.userservice.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }

    public User mapToUser(RegisterRequest registerRequest) {
        return User.builder()
                .firstname(registerRequest.firstname())
                .lastname(registerRequest.lastname())
                .username(registerRequest.username())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .phone(registerRequest.phone())
                .role(UserRole.PATIENT)
                .status(UserStatus.ACTIVE)
                .build();
    }

}
