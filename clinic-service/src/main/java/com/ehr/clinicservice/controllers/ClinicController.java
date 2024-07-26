package com.ehr.clinicservice.controllers;

import com.ehr.clinicservice.dto.requests.ClinicRequest;
import com.ehr.clinicservice.services.ClinicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clinic")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ClinicController {

    private final ClinicService clinicService;

    @GetMapping("/getByName")
    public ResponseEntity<Object> getByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(clinicService.getClinicByName(name), HttpStatus.OK);
    }

    @GetMapping("/getAllClinics")
    public ResponseEntity<Object> getAllClinics() {
        return new ResponseEntity<>(clinicService.getAllClinics(), HttpStatus.OK);
    }

    @PostMapping("/addClinic")
    public ResponseEntity<Object> addClinic(@Valid @RequestBody ClinicRequest clinicRequest) {
        clinicService.addClinic(clinicRequest);
        return new ResponseEntity<>("Clinic added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/editClinic/{clinicId}")
    public ResponseEntity<Object> editClinic(@Valid @RequestBody ClinicRequest clinicRequest, @PathVariable("clinicId") Long clinicId) {
        clinicService.editClinic(clinicRequest, clinicId);
        return new ResponseEntity<>("Clinic updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteClinic/{clinicId}")
    public ResponseEntity<Object> deleteClinic(@PathVariable("clinicId") Long clinicId) {
        clinicService.deleteClinic(clinicId);
        return new ResponseEntity<>("Clinic deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> test() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

}
