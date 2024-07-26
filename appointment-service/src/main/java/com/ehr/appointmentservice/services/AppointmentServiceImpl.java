package com.ehr.appointmentservice.services;

import com.ehr.appointmentservice.dto.requests.AppointmentRequest;
import com.ehr.appointmentservice.dto.responses.AppointmentResponse;
import com.ehr.appointmentservice.enums.AppointmentStatus;
import com.ehr.appointmentservice.mappers.AppointmentMapper;
import com.ehr.appointmentservice.models.Appointment;
import com.ehr.appointmentservice.repositories.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public List<AppointmentResponse> getAvailableAppointments() {
        return appointmentRepository.findAllByStatusIs(AppointmentStatus.AVAILABLE)
                .stream()
                .map(appointmentMapper::mapToAppointmentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void addAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = appointmentMapper.mapToAppointment(appointmentRequest);
        appointmentRepository.save(appointment);
        log.info("Appointment added successfully");
    }

    @Override
    public void bookAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        if (appointment.getStatus().equals(AppointmentStatus.AVAILABLE)) {
            appointment.setStatus(AppointmentStatus.BOOKED);
            appointmentRepository.save(appointment);
            log.info("Appointment booked successfully");
        }
    }

}
