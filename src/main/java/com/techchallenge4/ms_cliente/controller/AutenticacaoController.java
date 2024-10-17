package com.techchallenge4.ms_cliente.controller;

import com.techchallenge4.ms_cliente.controller.dto.request.AutenticacaoRequest;
import com.techchallenge4.ms_cliente.controller.dto.response.TokenResponse;
import com.techchallenge4.ms_cliente.domain.model.usuario.Usuario;
import com.techchallenge4.ms_cliente.infra.security.utils.token.TokenUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenUtils tokenUtils;

    @PostMapping
    public ResponseEntity<TokenResponse> efetuarLogin(@Valid @RequestBody AutenticacaoRequest request) {
        var token = new UsernamePasswordAuthenticationToken(request.login(), request.senha());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenUtils.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(tokenJWT));
    }
}
