package com.ehr.userservice.controllers;

import com.ehr.userservice.dto.requests.RegisterRequest;
import com.ehr.userservice.services.AuthService;
import com.ehr.userservice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(authService.registerUser(registerRequest), HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(authService.getUserByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userService.getUserById(#id).username == principal")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

}
