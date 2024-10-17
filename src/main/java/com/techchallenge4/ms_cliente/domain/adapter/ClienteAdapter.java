package com.techchallenge4.ms_cliente.domain.adapter;

import com.techchallenge4.ms_cliente.domain.model.cliente.Cliente;
import com.techchallenge4.ms_cliente.controller.dto.request.ClienteRequest;
import com.techchallenge4.ms_cliente.controller.dto.response.ClienteResponse;

public interface ClienteAdapter {

    Cliente clienteRequestToCliente(ClienteRequest request);

    ClienteResponse clienteToClienteResponse(Cliente cliente);
}
