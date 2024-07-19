package com.ehr.userservice.dto;

import com.ehr.userservice.enums.UserRole;
import com.ehr.userservice.enums.UserStatus;
import lombok.Data;

@Data
public class AuthUserDto {
    private Long userId;
    private String username;
    private String email;
    private String phone;
    private String password;
    private UserRole role;
    private UserStatus status;
}