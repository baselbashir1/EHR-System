package com.ehr.appointmentservice.controlles;

import com.ehr.appointmentservice.dto.requests.AppointmentRequest;
import com.ehr.appointmentservice.services.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/getAvailableAppointments")
    public ResponseEntity<Object> getAvailableAppointments() {
        return new ResponseEntity<>(appointmentService.getAvailableAppointments(), HttpStatus.OK);
    }

    @PostMapping("/addAppointment")
    public ResponseEntity<Object> addAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.addAppointment(appointmentRequest);
        return new ResponseEntity<>("Appointment add successfully", HttpStatus.CREATED);
    }

    @PostMapping("/bookAppointment/{appointmentId}")
    public ResponseEntity<Object> bookAppointment(@PathVariable("appointmentId") Long appointmentId) {
        appointmentService.bookAppointment(appointmentId);
        return new ResponseEntity<>("Appointment booked successfully", HttpStatus.OK);
    }

}
