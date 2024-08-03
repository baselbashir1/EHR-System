package com.ehr.userservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ehr.userservice.enums.Permission.*;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_CREATE,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            DOCTOR_READ,
            DOCTOR_CREATE,
            DOCTOR_UPDATE,
            DOCTOR_DELETE
    )),
    DOCTOR(Set.of(
            DOCTOR_READ,
            DOCTOR_CREATE,
            DOCTOR_UPDATE,
            DOCTOR_DELETE
    )),
    PATIENT(Collections.emptySet()),
    RECEPTION(Collections.emptySet()),
    SECRETARY(Collections.emptySet()),
    NURSE(Collections.emptySet()),
    ACCOUNTANT(Collections.emptySet());

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
