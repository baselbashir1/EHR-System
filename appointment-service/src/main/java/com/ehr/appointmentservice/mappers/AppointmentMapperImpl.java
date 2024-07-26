package com.ehr.appointmentservice.mappers;

import com.ehr.appointmentservice.dto.requests.AppointmentRequest;
import com.ehr.appointmentservice.dto.responses.AppointmentResponse;
import com.ehr.appointmentservice.models.Appointment;
import org.springframework.stereotype.Service;

@Service
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentResponse mapToAppointmentResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .secretaryId(appointment.getSecretaryId())
                .clinicId(appointment.getClinicId())
                .appointmentDate(appointment.getAppointmentDate())
                .status(appointment.getStatus())
                .build();
    }

    @Override
    public Appointment mapToAppointment(AppointmentRequest appointmentRequest) {
        return Appointment.builder()
                .patientId(appointmentRequest.patientId())
                .doctorId(appointmentRequest.doctorId())
                .secretaryId(appointmentRequest.secretaryId())
                .clinicId(appointmentRequest.clinicId())
                .appointmentDate(appointmentRequest.appointmentDate())
                .status(appointmentRequest.status())
                .build();
    }

}
