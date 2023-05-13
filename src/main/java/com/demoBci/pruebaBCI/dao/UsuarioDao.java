package com.demoBci.pruebaBCI.dao;

import com.demoBci.pruebaBCI.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioDao extends JpaRepository <Usuario,Long> {
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(UUID id);

}
