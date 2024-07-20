package com.ehr.appointmentservice.repositories;

import com.ehr.appointmentservice.models.AppointmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory, Long> {
}
