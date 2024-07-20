package com.ehr.appointmentservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment extends BaseModel {

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "secretary_id", nullable = false)
    private Long secretaryId;

    @Column(name = "clinic_id", nullable = false)
    private Long clinicId;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "status")
    private String status;

}