package com.demoBci.pruebaBCI.dao;

import com.demoBci.pruebaBCI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDao extends JpaRepository <User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID id);

}
