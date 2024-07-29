package com.ehr.userservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN(Set.of(
            Permission.ADMIN_READ,
            Permission.ADMIN_CREATE,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE,
            Permission.DOCTOR_READ,
            Permission.DOCTOR_CREATE,
            Permission.DOCTOR_UPDATE,
            Permission.DOCTOR_DELETE
    )),
    DOCTOR(Set.of(
            Permission.DOCTOR_READ,
            Permission.DOCTOR_CREATE,
            Permission.DOCTOR_UPDATE,
            Permission.DOCTOR_DELETE
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
