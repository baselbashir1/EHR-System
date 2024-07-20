package com.ehr.appointmentservice.repositories;

import com.ehr.appointmentservice.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
