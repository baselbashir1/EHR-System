package com.ehr.userservice.controllers;

import com.ehr.userservice.dto.requests.UserRequest;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(authService.registerUser(registerRequest), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> addUser(@Valid @ModelAttribute UserRequest userRequest) {
        userService.addUser(userRequest);
        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/editUser/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> editUser(@Valid @RequestBody UserRequest userRequest, @PathVariable("userId") Long userId) {
        userService.editUser(userRequest, userId);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(authService.getUserByUsername(username), HttpStatus.OK);
    }

}
