package com.ehr.userservice.services;

import com.ehr.userservice.dto.requests.UserRequest;
import com.ehr.userservice.dto.responses.UserResponse;
import com.ehr.userservice.enums.UserRole;
import com.ehr.userservice.enums.UserStatus;
import com.ehr.userservice.mappers.UserMapper;
import com.ehr.userservice.models.Doctor;
import com.ehr.userservice.models.Secretary;
import com.ehr.userservice.models.User;
import com.ehr.userservice.repositories.DoctorRepository;
import com.ehr.userservice.repositories.SecretaryRepository;
import com.ehr.userservice.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final SecretaryRepository secretaryRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAllByStatus(UserStatus.ACTIVE)
                .stream()
                .map(userMapper::mapToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addUser(UserRequest userRequest) {
        validateUser(userRequest.username(), userRequest.email());
        User user = userRepository.save(userMapper.mapToUser(userRequest));
        insertUserToTargetTable(user, userRequest);
        log.info("User {} added successfully", user.getId());
    }

    @Override
    @Transactional
    public void editUser(UserRequest userRequest, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        validateUser(userRequest.username(), userRequest.email());
        User updatedUser = userMapper.mapToUser(user, userRequest);
        userRepository.save(updatedUser);
        log.info("User {} updated successfully", updatedUser.getId());
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.delete(user);
        log.info("User {} deleted successfully", user.getId());
    }

    public void validateUser(String username, String email) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    public void insertUserToTargetTable(User user, UserRequest userRequest) {
        if (userRequest.role().equals(UserRole.DOCTOR)) {
            Doctor doctor = userMapper.mapToDoctor(user, userRequest);
            doctorRepository.save(doctor);
            log.info("Doctor added successfully");
        }

        if (userRequest.role().equals(UserRole.SECRETARY)) {
            Secretary secretary = userMapper.mapToSecretary(user, userRequest);
            secretaryRepository.save(secretary);
            log.info("Secretary added successfully");
        }
    }

}