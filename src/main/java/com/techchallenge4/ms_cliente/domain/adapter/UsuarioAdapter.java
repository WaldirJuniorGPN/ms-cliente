package com.techchallenge4.ms_cliente.domain.adapter;

import com.techchallenge4.ms_cliente.controller.dto.request.UsuarioRequest;
import com.techchallenge4.ms_cliente.controller.dto.response.UsuarioResponse;
import com.techchallenge4.ms_cliente.domain.model.usuario.Usuario;

public interface UsuarioAdapter {

    Usuario usuarioRequestToUsuario(UsuarioRequest request);

    UsuarioResponse usuarioToUsuarioResponse(Usuario usuario);
}
