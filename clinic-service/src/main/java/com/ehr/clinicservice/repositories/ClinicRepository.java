package com.ehr.clinicservice.repositories;

import com.ehr.clinicservice.models.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Optional<Clinic> findByName(String name);

}