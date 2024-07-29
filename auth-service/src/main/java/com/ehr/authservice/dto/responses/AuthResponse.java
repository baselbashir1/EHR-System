package com.ehr.authservice.dto.responses;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        String message,
        Integer statusCode
) {
}
