package com.ehr.authservice.services;

public interface JwtService {

    String generateToken(String username);

}
