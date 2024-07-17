package com.ehr.userservice.repositories;

import com.ehr.userservice.models.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretaryRepository extends JpaRepository<Secretary, Long> {
}
