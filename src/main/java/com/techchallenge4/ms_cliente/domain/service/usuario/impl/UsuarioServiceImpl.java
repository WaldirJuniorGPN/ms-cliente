package com.techchallenge4.ms_cliente.domain.service.usuario.impl;

import com.techchallenge4.ms_cliente.controller.dto.request.UsuarioRequest;
import com.techchallenge4.ms_cliente.controller.dto.response.UsuarioResponse;
import com.techchallenge4.ms_cliente.domain.adapter.UsuarioAdapter;
import com.techchallenge4.ms_cliente.domain.service.usuario.UsuarioService;
import com.techchallenge4.ms_cliente.infra.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioAdapter adapter;
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponse criar(UsuarioRequest request) {
        var usuario = adapter.usuarioRequestToUsuario(request);
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        repository.save(usuario);

        return adapter.usuarioToUsuarioResponse(usuario);
    }
}
