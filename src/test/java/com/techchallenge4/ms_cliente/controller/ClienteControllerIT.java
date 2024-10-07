package com.techchallenge4.ms_cliente.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techchallenge4.ms_cliente.model.dto.request.ClienteRequest;
import com.techchallenge4.ms_cliente.model.dto.response.ClienteResponse;
import com.techchallenge4.ms_cliente.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.techchallenge4.ms_cliente.mock.ClienteDados.getClienteRequest;
import static com.techchallenge4.ms_cliente.mock.ClienteDados.getClienteResponse;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
@WebMvcTest(ClienteController.class)
class ClienteControllerIT {

    private ClienteRequest clienteRequest;
    private ClienteResponse clienteResponse;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        clienteRequest = getClienteRequest();
        clienteResponse = getClienteResponse();
    }

    @Test
    void deveCriarClienteERetornarCreated() throws Exception {

        when(clienteService.criar(any(ClienteRequest.class))).thenReturn(clienteResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/clientes/1"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("João da Silva Sauro")));
    }

    @Test
    void deveAtualizarClienteERetornarOk() throws Exception {

        when(clienteService.atualizar(eq(1L), any(ClienteRequest.class))).thenReturn(clienteResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("João da Silva Sauro")));
    }

    @Test
    void deveBuscarClientePorIdERetornarOk() throws Exception {

        when(clienteService.buscar(1L)).thenReturn(clienteResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("João da Silva Sauro")));
    }

    @Test
    void deveListarTodosClientesERetornarOk() throws Exception {
        List<ClienteResponse> clientes = List.of(clienteResponse);

        when(clienteService.listarTodos(any())).thenReturn(new PageImpl<>(clientes));

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].nome", is("João da Silva Sauro")));
    }

    @Test
    void deveDeletarClienteERetornarNoContent() throws Exception {
        Mockito.doNothing().when(clienteService).deletar(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/1"))
                .andExpect(status().isNoContent());
    }
}
