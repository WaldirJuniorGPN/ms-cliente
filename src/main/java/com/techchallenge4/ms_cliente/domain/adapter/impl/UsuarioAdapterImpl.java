package com.techchallenge4.ms_cliente.domain.adapter.impl;

import com.techchallenge4.ms_cliente.controller.dto.request.UsuarioRequest;
import com.techchallenge4.ms_cliente.controller.dto.response.UsuarioResponse;
import com.techchallenge4.ms_cliente.domain.adapter.UsuarioAdapter;
import com.techchallenge4.ms_cliente.domain.model.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioAdapterImpl implements UsuarioAdapter {

    private final ModelMapper mapper;

    @Override
    public Usuario usuarioRequestToUsuario(UsuarioRequest request) {
        return mapper.map(request, Usuario.class);
    }

    @Override
    public UsuarioResponse usuarioToUsuarioResponse(Usuario usuario) {
        return mapper.map(usuario, UsuarioResponse.class);
    }
}
