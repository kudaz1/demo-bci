package com.demoBci.pruebaBCI.dao;

import com.demoBci.pruebaBCI.entity.Phones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhonesDao extends JpaRepository <Phones,Long> {
    List<Phones> findByUserId(String userId);


}
