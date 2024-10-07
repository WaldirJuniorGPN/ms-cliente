package com.techchallenge4.ms_cliente.service.impl;

import com.techchallenge4.ms_cliente.adapter.ClienteAdapter;
import com.techchallenge4.ms_cliente.model.Cliente;
import com.techchallenge4.ms_cliente.model.dto.request.ClienteRequest;
import com.techchallenge4.ms_cliente.model.dto.response.ClienteResponse;
import com.techchallenge4.ms_cliente.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static com.techchallenge4.ms_cliente.mock.ClienteDados.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    private AutoCloseable closeable;
    private ClienteRequest request;
    private Cliente cliente;
    private ClienteResponse response;
    private Long id;

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteAdapter adapter;

    @InjectMocks
    private ClienteServiceImpl service;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        request = getClienteRequest();
        cliente = getCliente();
        response = getClienteResponse();
        id = 1L;
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void criar_deveRetornarClienteResponse_quandoSucesso() {

        when(adapter.clienteRequestToCliente(request)).thenReturn(cliente);
        when(repository.save(cliente)).thenReturn(cliente);
        when(adapter.clienteToClienteResponse(cliente)).thenReturn(response);

        var resultado = service.criar(request);

        assertNotNull(resultado);
        assertEquals(response.getId(), resultado.getId());
        assertEquals(response.getNome(), resultado.getNome());
        verify(repository, times(1)).save(cliente);
    }

    @Test
    void atualizar_deveRetornarClienteResponse_quandoCienteExiste() {

        when(repository.findByIdAndAtivoTrue(id)).thenReturn(Optional.of(cliente));
        when((adapter.clienteRequestToCliente(request))).thenReturn(cliente);
        when((repository.save(cliente))).thenReturn(cliente);
        when(adapter.clienteToClienteResponse(cliente)).thenReturn(response);

        var resultado = service.atualizar(id, request);

        assertNotNull(resultado);
        assertEquals(response, resultado);
        verify(repository, times(1)).save(cliente);
    }

    @Test
    void buscar_deveRetornarClienteResponse_quandoClienteExiste() {

        when(repository.findByIdAndAtivoTrue(id)).thenReturn(Optional.of(cliente));
        when((adapter.clienteToClienteResponse(cliente))).thenReturn(response);

        var resultado = service.buscar(id);

        assertNotNull(resultado);
        assertEquals(response, resultado);
        verify(repository, times(1)).findByIdAndAtivoTrue(id);
    }

    @Test
    void listarTodos_deveRetornarPaginaDeClientes() {

        var pageRequest = PageRequest.of(0, 10);
        var clientePage = new PageImpl<>(List.of(cliente), pageRequest, 1);

        when(repository.findAllByAtivoTrue(pageRequest)).thenReturn(clientePage);
        when(adapter.clienteToClienteResponse(cliente)).thenReturn(response);

        var resultado = service.listarTodos(pageRequest);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(repository, times(1)).findAllByAtivoTrue(pageRequest);
    }

    @Test
    void deletar_deveDesativarCliente_quandoClienteExiste() {

        when(repository.findByIdAndAtivoTrue(id)).thenReturn(Optional.of(cliente));

        service.deletar(id);

        assertFalse(cliente.isAtivo());
        verify(repository, times(1)).findByIdAndAtivoTrue(id);
    }

    @Test
    void buscar_deveLancarEntityNotFoundExceptioin_quandoClienteNaoExiste() {

        when(repository.findByIdAndAtivoTrue(id)).thenReturn(Optional.empty());

        var exception = assertThrows(EntityNotFoundException.class,
                () -> service.buscar(id));

        assertEquals("Cliente não encontrado", exception.getMessage());
    }
}