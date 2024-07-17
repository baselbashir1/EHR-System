package com.ehr.userservice.repositories;

import com.ehr.userservice.enums.UserStatus;
import com.ehr.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findAllByStatus(UserStatus status);

}
