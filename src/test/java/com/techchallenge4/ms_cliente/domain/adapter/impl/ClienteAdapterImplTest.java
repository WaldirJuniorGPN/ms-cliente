package com.techchallenge4.ms_cliente.domain.adapter.impl;

import com.techchallenge4.ms_cliente.controller.dto.response.ClienteResponse;
import com.techchallenge4.ms_cliente.domain.model.cliente.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static com.techchallenge4.ms_cliente.mock.ClienteDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteAdapterImplTest {

    private AutoCloseable closeable;
    private Cliente cliente;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteAdapterImpl clienteAdapter;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        cliente = getCliente();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void clienteRequestToClienteDeveRetornarCliente() {
        var request = getClienteRequest();
        var resultado = clienteAdapter.clienteRequestToCliente(request);

        assertEquals(cliente.getNome(), resultado.getNome());
        verify(modelMapper, times(1)).map(request, Cliente.class);
    }

    @Test
    void clienteToClienteResponseDeveRetornarClienteResponse() {
        var response = getClienteResponse();
        var resultado = clienteAdapter.clienteToClienteResponse(cliente);

        assertEquals(response, resultado);
        verify(modelMapper, times(1)).map(cliente, ClienteResponse.class);
    }
}