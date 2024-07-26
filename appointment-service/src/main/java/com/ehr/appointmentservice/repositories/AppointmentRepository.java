package com.ehr.appointmentservice.repositories;

import com.ehr.appointmentservice.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByStatusIs(String status);

}
