package com.techchallenge4.ms_cliente.repository;

import com.techchallenge4.ms_cliente.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByIdAndAtivoTrue(Long id);

    Page<Cliente> findAllByAtivoTrue(Pageable pageable);
}
