package com.techchallenge4.ms_cliente.controller;

import com.techchallenge4.ms_cliente.controller.dto.request.AutenticacaoRequest;
import com.techchallenge4.ms_cliente.controller.dto.response.TokenResponse;
import com.techchallenge4.ms_cliente.domain.model.cliente.Cliente;
import com.techchallenge4.ms_cliente.infra.security.utils.token.TokenUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenUtils tokenUtils;

    @PostMapping
    public ResponseEntity<TokenResponse> efetuarLogin(@Valid @RequestBody AutenticacaoRequest request) {
        var token = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenUtils.gerarToken((Cliente) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(tokenJWT));
    }

    @GetMapping
    public ResponseEntity<String> validarLogin(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (Cliente) authentication.getPrincipal();
        log.info("Usuario logado: {}", usuario.getId());
        return ResponseEntity.ok(usuario.getId().toString());
    }

}
