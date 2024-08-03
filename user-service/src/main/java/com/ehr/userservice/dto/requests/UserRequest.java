package com.ehr.userservice.dto.requests;

import com.ehr.userservice.enums.UserRole;
import com.ehr.userservice.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

public record UserRequest(
        @NotNull(message = "Firstname is required")
        @NotEmpty(message = "Firstname is required")
        String firstname,
        @NotNull(message = "Lastname is required")
        @NotEmpty(message = "Lastname is required")
        String lastname,
        @NotNull(message = "Username is required")
        @NotEmpty(message = "Username is required")
        String username,
        @NotNull(message = "Email is required")
        @NotEmpty(message = "Email is required")
        @Email(message = "Invalid email")
        String email,
        @NotNull(message = "Phone number is required")
        @NotEmpty(message = "Phone number is required")
        @Pattern(regexp = "^[0-9]{10,}$", message = "Invalid mobile number")
        String phone,
        @NotNull(message = "Password is required")
        @NotEmpty(message = "Password is required")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must be at least 8 characters and contain at least one letter and one number")
        String password,
        UserRole role,
        UserStatus status,
        MultipartFile image,
        Long clinicId,
        String doctorSpecialty,
        Long doctorId
) {
}