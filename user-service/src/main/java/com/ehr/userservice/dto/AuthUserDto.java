package com.ehr.userservice.dto;

import com.ehr.userservice.enums.UserRole;
import lombok.Data;

@Data
public class AuthUserDto {
    private Long id;
    private String username;
    private String password;
    private UserRole role;
}