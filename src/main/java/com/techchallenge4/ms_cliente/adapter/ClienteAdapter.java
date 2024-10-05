package com.techchallenge4.ms_cliente.adapter;

import com.techchallenge4.ms_cliente.model.Cliente;
import com.techchallenge4.ms_cliente.model.dto.request.ClienteRequest;
import com.techchallenge4.ms_cliente.model.dto.response.ClienteResponse;

public interface ClienteAdapter {

    Cliente clienteRequestToCliente(ClienteRequest request);

    ClienteResponse clienteToClienteResponse(Cliente cliente);
}
