package com.techchallenge4.ms_cliente.service;

import com.techchallenge4.ms_cliente.model.dto.request.ClienteRequest;
import com.techchallenge4.ms_cliente.model.dto.response.ClienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    ClienteResponse criar(ClienteRequest request);

    ClienteResponse atualizar(Long id, ClienteRequest request);

    ClienteResponse buscar(Long id);

    Page<ClienteResponse> listarTodos(Pageable pageable);

    void deletar(Long id);
}
