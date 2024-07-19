package com.ehr.userservice.dto;

import com.ehr.userservice.enums.UserRole;
import com.ehr.userservice.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private String phone;
    private UserRole role;
    private UserStatus status;
}
