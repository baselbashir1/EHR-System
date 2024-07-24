package com.ehr.userservice.mappers;

import com.ehr.userservice.dto.requests.RegisterRequest;
import com.ehr.userservice.dto.requests.UserRequest;
import com.ehr.userservice.dto.responses.UserResponse;
import com.ehr.userservice.models.Doctor;
import com.ehr.userservice.models.Secretary;
import com.ehr.userservice.models.User;

public interface UserMapper {

    UserResponse mapToUserResponse(User user);

    User mapToUser(RegisterRequest registerRequest);

    User mapToUser(UserRequest userRequest);

    User mapToUser(User user, UserRequest userRequest);

    Doctor mapToDoctor(User user, UserRequest userRequest);

    Secretary mapToSecretary(User user, UserRequest userRequest);

}
