package com.techchallenge4.ms_cliente.controller;

import com.techchallenge4.ms_cliente.controller.dto.request.UsuarioRequest;
import com.techchallenge4.ms_cliente.controller.dto.response.UsuarioResponse;
import com.techchallenge4.ms_cliente.domain.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@Valid @RequestBody UsuarioRequest request, UriComponentsBuilder uriComponentsBuilder) {
        var response = service.criar(request);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
