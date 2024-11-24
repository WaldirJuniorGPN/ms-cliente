package com.techchallenge4.ms_cliente.controller;

import com.techchallenge4.ms_cliente.controller.dto.request.AutenticacaoRequest;
import com.techchallenge4.ms_cliente.infra.security.utils.token.TokenUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Objects;

import static com.techchallenge4.ms_cliente.mock.ClienteDados.getCliente;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AutenticacaoControllerTest {

    private AutoCloseable closeable;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenUtils tokenUtils;

    @InjectMocks
    private AutenticacaoController autenticacaoController;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void efetuarLoginDeveRetornarTokenComSucesso() {

        var email = "teste@teste.com";
        var senha = "senha123";
        var expectativaToken = "mock-jwt-token";

        var request = new AutenticacaoRequest(email, senha);
        var authentication = mock(Authentication.class);
        var cliente = getCliente();

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(cliente);
        when(tokenUtils.gerarToken(cliente)).thenReturn(expectativaToken);

        var response = autenticacaoController.efetuarLogin(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectativaToken, Objects.requireNonNull(response.getBody()).token());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenUtils, times(1)).gerarToken(cliente);
    }
}