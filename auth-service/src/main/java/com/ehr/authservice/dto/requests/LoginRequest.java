package com.ehr.authservice.dto.requests;

public record LoginRequest(
        String username,
        String password
) {
}
