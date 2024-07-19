package com.ehr.userservice.services;

import com.ehr.userservice.dto.responses.UserResponse;
import com.ehr.userservice.enums.UserStatus;
import com.ehr.userservice.mappers.UserMapper;
import com.ehr.userservice.models.User;
import com.ehr.userservice.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User save(User user) {
        return userRepository.save(user);
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    protected User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    protected User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAllByStatus(UserStatus.ACTIVE)
                .stream()
                .map(userMapper::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        return userMapper.mapToUserResponse(findUserById(id));
    }

    public UserResponse getUserByEmail(String email) {
        return userMapper.mapToUserResponse(findUserByEmail(email));
    }

    public UserResponse getUserByUsername(String username) {
        return userMapper.mapToUserResponse(findUserByUsername(username));
    }

    public void deleteUserById(Long id) {
        User user = findUserById(id);
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }

}