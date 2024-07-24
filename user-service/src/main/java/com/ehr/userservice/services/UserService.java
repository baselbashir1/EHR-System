package com.ehr.userservice.services;

import com.ehr.userservice.dto.requests.UserRequest;
import com.ehr.userservice.dto.responses.UserResponse;
import com.ehr.userservice.models.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findUserByUsername(String username);

    List<UserResponse> getAllUsers();

    void addUser(UserRequest userRequest);

    void editUser(UserRequest userRequest, Long userId);

    void deleteUser(Long userId);

}
