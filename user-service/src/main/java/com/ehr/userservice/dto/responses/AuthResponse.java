package com.ehr.userservice.dto.responses;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        String message,
        Integer statusCode
) {
}