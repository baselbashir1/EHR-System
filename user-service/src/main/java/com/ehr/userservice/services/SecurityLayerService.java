package com.ehr.userservice.services;

import com.ehr.userservice.dto.responses.UserRoleResponse;

public interface SecurityLayerService {

    UserRoleResponse getUserIdAndRoleFromToken();

}
