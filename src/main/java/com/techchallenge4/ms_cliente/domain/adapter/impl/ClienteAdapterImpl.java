package com.techchallenge4.ms_cliente.domain.adapter.impl;

import com.techchallenge4.ms_cliente.domain.model.cliente.Cliente;
import com.techchallenge4.ms_cliente.controller.dto.request.ClienteRequest;
import com.techchallenge4.ms_cliente.controller.dto.response.ClienteResponse;
import com.techchallenge4.ms_cliente.domain.adapter.ClienteAdapter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteAdapterImpl implements ClienteAdapter {

    private final ModelMapper mapper;

    @Override
    public Cliente clienteRequestToCliente(ClienteRequest request) {
        return mapper.map(request, Cliente.class);
    }

    @Override
    public ClienteResponse clienteToClienteResponse(Cliente cliente) {
        return mapper.map(cliente, ClienteResponse.class);
    }
}
