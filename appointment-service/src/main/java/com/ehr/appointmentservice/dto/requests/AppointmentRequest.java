package com.ehr.appointmentservice.dto.requests;

import com.ehr.appointmentservice.enums.AppointmentStatus;

import java.util.Date;
public record AppointmentRequest(
        Long patientId,
        Long doctorId,
        Long secretaryId,
        Long clinicId,
        Date appointmentDate,
        AppointmentStatus status
) {
}
