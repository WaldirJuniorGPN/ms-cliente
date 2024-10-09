package com.techchallenge4.ms_cliente.steps;

import com.techchallenge4.ms_cliente.adapter.ClienteAdapter;
import com.techchallenge4.ms_cliente.model.Cliente;
import com.techchallenge4.ms_cliente.model.dto.request.ClienteRequest;
import com.techchallenge4.ms_cliente.model.dto.response.ClienteResponse;
import com.techchallenge4.ms_cliente.repository.ClienteRepository;
import com.techchallenge4.ms_cliente.service.impl.ClienteServiceImpl;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;

import static com.techchallenge4.ms_cliente.mock.ClienteDados.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClienteSteps {

    private AutoCloseable closeable;
    private ClienteRequest clienteRequest;
    private ClienteResponse clienteResponse;

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteAdapter adapter;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        clienteRequest = getClienteRequest();
        clienteResponse = getClienteResponse();
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Dado("que eu tenha um novo cliente com o nome {string}")
    public void que_eu_tenha_um_novo_cliente_com_o_nome(String string) {
        clienteRequest.setNome(string);

        var cliente = getCliente();
        when(adapter.clienteRequestToCliente(clienteRequest)).thenReturn(cliente);
        when(repository.save(cliente)).thenReturn(cliente);
        when(adapter.clienteToClienteResponse(cliente)).thenReturn(clienteResponse);
    }

    @Quando("eu envio a solicitação de criação de cliente")
    public void eu_envio_a_solicitação_de_criação_de_cliente() {
        clienteResponse = clienteService.criar(clienteRequest);
    }

    @Entao("o cliente deve ser criado com sucesso")
    public void o_cliente_deve_ser_criado_com_sucesso() {
        assertNotNull(clienteResponse);
        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Entao("eu devo receber uma resposta com o ID do cliente")
    public void eu_devo_receber_uma_resposta_com_o_id_do_cliente() {
        assertNotNull(clienteResponse.getId());
    }
}
