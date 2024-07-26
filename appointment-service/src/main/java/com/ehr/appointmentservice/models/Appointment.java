package com.ehr.appointmentservice.models;

import com.ehr.appointmentservice.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppointmentStatus status;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private AppointmentHistory appointmentHistory;

}