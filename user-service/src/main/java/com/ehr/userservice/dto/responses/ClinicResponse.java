package com.ehr.userservice.dto.responses;

import lombok.Builder;

@Builder
public record ClinicResponse(
        Long clinicId,
        String name,
        String address,
        String phoneNumber
) {
}