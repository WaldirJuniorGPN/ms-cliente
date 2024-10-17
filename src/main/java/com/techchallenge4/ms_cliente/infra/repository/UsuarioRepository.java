package com.techchallenge4.ms_cliente.infra.repository;

import com.techchallenge4.ms_cliente.domain.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLoginAndAtivoTrue(String login);
}
