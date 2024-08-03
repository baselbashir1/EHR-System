package com.ehr.userservice.services;

import com.ehr.userservice.dto.responses.UserRoleResponse;

import java.util.List;

public interface SecurityLayerService {

    UserRoleResponse getUserDetails();

    void checkRole(String role);

    void checkAuthorities(List<String> authorities);

}
