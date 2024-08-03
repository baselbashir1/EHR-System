package com.ehr.userservice.controllers;

import com.ehr.userservice.dto.requests.UserRequest;
import com.ehr.userservice.services.SecurityLayerService;
import com.ehr.userservice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SecurityLayerService securityLayerService;

    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@Valid @ModelAttribute UserRequest userRequest) {
        userService.addUser(userRequest);
        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/editUser/{userId}")
    public ResponseEntity<Object> editUser(@Valid @RequestBody UserRequest userRequest, @PathVariable("userId") Long userId) {
        userService.editUser(userRequest, userId);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.loadUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        securityLayerService.checkAuthorities(List.of("admin:read", "doctor:read"));
        return new ResponseEntity<>("True", HttpStatus.OK);
    }

}
