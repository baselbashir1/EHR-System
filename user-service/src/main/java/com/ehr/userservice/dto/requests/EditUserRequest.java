package com.ehr.userservice.dto.requests;

import com.ehr.userservice.enums.UserRole;
import com.ehr.userservice.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record EditUserRequest(
        String firstname,
        String lastname,
        String username,
        @Email(message = "Invalid email")
        String email,
        @Pattern(regexp = "^[0-9]{10,}$", message = "Invalid mobile number")
        String phone,
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must be at least 8 characters and contain at least one letter and one number")
        String password,
        UserRole role,
        UserStatus status
) {
}