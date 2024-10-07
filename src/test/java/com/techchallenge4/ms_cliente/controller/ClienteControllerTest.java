package com.techchallenge4.ms_cliente.controller;

import com.techchallenge4.ms_cliente.model.dto.request.ClienteRequest;
import com.techchallenge4.ms_cliente.model.dto.response.ClienteResponse;
import com.techchallenge4.ms_cliente.service.ClienteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

import static com.techchallenge4.ms_cliente.mock.ClienteDados.getClienteRequest;
import static com.techchallenge4.ms_cliente.mock.ClienteDados.getClienteResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    private AutoCloseable closeable;
    private ClienteRequest request;
    private ClienteResponse response;
    private Long id;

    @Mock
    private ClienteService service;

    @InjectMocks
    private ClienteController controller;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        request = getClienteRequest();
        response = getClienteResponse();
        id = 1L;
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void deveCriarClienteERetornarCreated() {
        var uriEsperada = "http://localhost/clientes/1";

        when(service.criar(request)).thenReturn(response);

        var resultado = controller.criar(request, UriComponentsBuilder.fromUriString("http://localhost"));

        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals(uriEsperada, Objects.requireNonNull(resultado.getHeaders().getLocation()).toString());
        assertEquals(response, resultado.getBody());
        verify(service, times(1)).criar(request);
    }

    @Test
    void deveAtualizarClienteERetornarOk() {
        when(service.atualizar(id, request)).thenReturn(response);

        var resultado = controller.atualizar(id, request);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(response, resultado.getBody());
        verify(service, times(1)).atualizar(id, request);
    }

    @Test
    void deveBuscarClientePorIdERetornarOk() {
        when(service.buscar(id)).thenReturn(response);

        var resultado = controller.buscr(id);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(response, resultado.getBody());
        verify(service, times(1)).buscar(id);
    }

    @Test
    void deveListarTodosClientesERetornarOk() {
        var pageable = PageRequest.of(0, 10);
        var pageResponse = new PageImpl<>(List.of(response));

        when(service.listarTodos(pageable)).thenReturn(pageResponse);

        var resultado = controller.listarTodos(pageable);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(pageResponse, resultado.getBody());
        verify(service, times(1)).listarTodos(pageable);
    }

    @Test
    void deveDeletarClienteERetornarNoContent() {
        doNothing().when(service).deletar(id);

        var resultado = controller.deletar(id);

        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
        verify(service, times(1)).deletar(id);
    }
}