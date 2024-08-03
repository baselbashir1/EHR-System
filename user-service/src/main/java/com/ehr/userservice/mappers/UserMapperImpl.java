package com.ehr.userservice.mappers;

import com.ehr.userservice.dto.requests.RegisterRequest;
import com.ehr.userservice.dto.requests.UserRequest;
import com.ehr.userservice.dto.responses.UserResponse;
import com.ehr.userservice.enums.UserRole;
import com.ehr.userservice.enums.UserStatus;
import com.ehr.userservice.models.Doctor;
import com.ehr.userservice.models.Secretary;
import com.ehr.userservice.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Override
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

    @Override
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

    @Override
    public User mapToUser(UserRequest userRequest) {
        return User.builder()
                .firstname(userRequest.username())
                .lastname(userRequest.lastname())
                .username(userRequest.username())
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .phone(userRequest.phone())
                .role(userRequest.role())
                .status(UserStatus.ACTIVE)
                .build();
    }

    @Override
    public User mapToUser(User user, UserRequest userRequest) {
        user.setFirstname(userRequest.firstname());
        user.setLastname(userRequest.lastname());
        user.setUsername(userRequest.username());
        user.setEmail(userRequest.email());
        user.setPhone(userRequest.phone());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setRole(userRequest.role());
        user.setStatus(userRequest.status());
        return user;
    }

    @Override
    public Doctor mapToDoctor(User user, UserRequest userRequest) {
        return Doctor.builder()
                .specialty(userRequest.doctorSpecialty())
                .clinicId(userRequest.clinicId())
                .user(user)
                .build();
    }

    @Override
    public Secretary mapToSecretary(User user, UserRequest userRequest) {
        return Secretary.builder()
                .doctorId(userRequest.doctorId())
                .user(user)
                .build();
    }

}
