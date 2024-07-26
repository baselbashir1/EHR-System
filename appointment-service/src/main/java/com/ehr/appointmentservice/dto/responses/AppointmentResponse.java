package com.ehr.appointmentservice.dto.responses;

import com.ehr.appointmentservice.enums.AppointmentStatus;
import lombok.Builder;

import java.util.Date;

@Builder
public record AppointmentResponse(
        Long patientId,
        Long doctorId,
        Long secretaryId,
        Long clinicId,
        Date appointmentDate,
        AppointmentStatus status
) {
}
