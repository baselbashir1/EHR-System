package com.ehr.appointmentservice.services;

import com.ehr.appointmentservice.dto.requests.AppointmentRequest;
import com.ehr.appointmentservice.dto.responses.AppointmentResponse;

import java.util.List;

public interface AppointmentService {

    List<AppointmentResponse> getAvailableAppointments();

    void addAppointment(AppointmentRequest appointmentRequest);

    void bookAppointment(Long appointmentId);

}
