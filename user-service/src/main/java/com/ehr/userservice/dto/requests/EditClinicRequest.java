package com.ehr.userservice.dto.requests;

public record EditClinicRequest(
        String name,
        String address,
        String phoneNumber
) {
}
