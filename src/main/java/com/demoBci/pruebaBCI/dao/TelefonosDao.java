package com.demoBci.pruebaBCI.dao;

import com.demoBci.pruebaBCI.entity.Telefonos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TelefonosDao extends JpaRepository <Telefonos,Long> {
    List<Telefonos> findByUserId(String userId);


}
