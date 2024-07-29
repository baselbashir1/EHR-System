package com.ehr.userservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),

    DOCTOR_READ("doctor:read"),
    DOCTOR_CREATE("doctor:create"),
    DOCTOR_UPDATE("doctor:update"),
    DOCTOR_DELETE("doctor:delete");

    private final String permission;

}
