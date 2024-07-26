package com.ehr.appointmentservice.mappers;

import com.ehr.appointmentservice.dto.requests.AppointmentRequest;
import com.ehr.appointmentservice.dto.responses.AppointmentResponse;
import com.ehr.appointmentservice.models.Appointment;

public interface AppointmentMapper {

    AppointmentResponse mapToAppointmentResponse(Appointment appointment);

    Appointment mapToAppointment(AppointmentRequest appointmentRequest);

}
