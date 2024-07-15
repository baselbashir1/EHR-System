package com.ehr.authservice.dto.requests;

public record RegisterRequest(
        String firstname,
        String lastname,
        String username,
        String email,
        String phone,
        String password
) {
}
