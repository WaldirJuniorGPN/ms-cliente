package com.techchallenge4.ms_cliente.domain.service.usuario;

import com.techchallenge4.ms_cliente.controller.dto.request.UsuarioRequest;
import com.techchallenge4.ms_cliente.controller.dto.response.UsuarioResponse;
import jakarta.validation.Valid;

public interface UsuarioService {

    UsuarioResponse criar(@Valid UsuarioRequest request);
}
