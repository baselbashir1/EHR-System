package com.ehr.userservice.controllers;

import com.ehr.userservice.dto.requests.RegisterRequest;
import com.ehr.userservice.dto.responses.AuthResponse;
import com.ehr.userservice.dto.responses.UserResponse;
import com.ehr.userservice.services.AuthService;
import com.ehr.userservice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(authService.registerUser(registerRequest), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<AuthResponse> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(authService.getUserByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userService.getUserById(#id).username == principal")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

}
