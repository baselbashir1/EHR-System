package com.ehr.userservice.services;

import com.ehr.userservice.dto.requests.RegisterRequest;
import com.ehr.userservice.dto.responses.AuthResponse;
import com.ehr.userservice.dto.responses.UserResponse;
import com.ehr.userservice.mappers.AuthMapper;
import com.ehr.userservice.mappers.UserMapper;
import com.ehr.userservice.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthMapper authMapper;
    private final UserMapper userMapper;

    public UserResponse registerUser(RegisterRequest registerRequest) {
        User user = userService.save(userMapper.mapToUser(registerRequest));
        return userMapper.mapToUserResponse(user);
    }

    public AuthResponse getUserByUsername(String username) {
        return authMapper.mapToAuthResponse(userService.findUserByUsername(username));
    }

}
