package com.ehr.userservice.services;

import com.ehr.userservice.dto.requests.UserRequest;
import com.ehr.userservice.dto.responses.UserResponse;
import com.ehr.userservice.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    User save(User user);

    User loadUserByUsername(String username) throws UsernameNotFoundException;

    List<UserResponse> getAllUsers();

    void addUser(UserRequest userRequest);

    void editUser(UserRequest userRequest, Long userId);

    void deleteUser(Long userId);

}
