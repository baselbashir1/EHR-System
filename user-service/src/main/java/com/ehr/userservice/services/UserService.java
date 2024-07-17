package com.ehr.userservice.services;

import com.ehr.userservice.dto.requests.RegisterRequest;
import com.ehr.userservice.enums.UserRole;
import com.ehr.userservice.enums.UserStatus;
import com.ehr.userservice.models.User;
import com.ehr.userservice.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(RegisterRequest registerRequest) {
        User toSave = User.builder()
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .email(registerRequest.email())
                .role(UserRole.PATIENT)
                .status(UserStatus.ACTIVE)
                .build();
        return userRepository.save(toSave);
    }

    public List<User> getAll() {
        return userRepository.findAllByStatus(UserStatus.ACTIVE);
    }

    public User getUserById(Long id) {
        return findUserById(id);
    }

    public User getUserByEmail(String email) {
        return findUserByEmail(email);
    }

    public User getUserByUsername(String username) {
        return findUserByUsername(username);
    }


    public void deleteUserById(Long id) {
        User toDelete = findUserById(id);
        toDelete.setStatus(UserStatus.INACTIVE);
        userRepository.save(toDelete);
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

}