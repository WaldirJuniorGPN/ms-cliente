package com.techchallenge4.ms_cliente.mock;

import com.techchallenge4.ms_cliente.model.Cliente;
import com.techchallenge4.ms_cliente.model.Endereco;
import com.techchallenge4.ms_cliente.model.dto.request.ClienteRequest;
import com.techchallenge4.ms_cliente.model.dto.request.EnderecoRequest;
import com.techchallenge4.ms_cliente.model.dto.response.ClienteResponse;
import com.techchallenge4.ms_cliente.model.dto.response.EnderecoResponse;

public interface ClienteDados {

    Long ID = 1L;
    String NOME = "João da Silva Sauro";
    String EMAIL = "joao.silva@email.com";
    String TELEFONE = "+55 (61) 99999-9999";
    String LOGRADOURO = "Rua das Flores";
    String BAIRRO = "Elefante Branco";
    String CIDADE = "Brasília";
    String UF = "DF";
    String CEP = "70000-000";

    static Cliente getCliente() {
        var cliente = new Cliente();
        cliente.setId(ID);
        cliente.setNome(NOME);
        cliente.setEmail(EMAIL);
        cliente.setTelefone(TELEFONE);
        cliente.setEndereco(getEndereco());

        return cliente;
    }

    static ClienteRequest getClienteRequest() {
        var clienteRequest = new ClienteRequest();
        clienteRequest.setNome(NOME);
        clienteRequest.setEmail(EMAIL);
        clienteRequest.setTelefone(TELEFONE);
        clienteRequest.setEndereco(getEnderecoRequest());

        return clienteRequest;
    }

    static ClienteResponse getClienteResponse() {
        var clienteResponse = new ClienteResponse();
        clienteResponse.setNome(NOME);
        clienteResponse.setEmail(EMAIL);
        clienteResponse.setTelefone(TELEFONE);
        clienteResponse.setEndereco(getEnderecoResponse());

        return clienteResponse;
    }

    private static Endereco getEndereco() {
        return new Endereco(LOGRADOURO, null, null, BAIRRO, CIDADE, UF, CEP);
    }

    private static EnderecoResponse getEnderecoResponse() {
        return new EnderecoResponse(LOGRADOURO, null, null, BAIRRO, CIDADE, UF, CEP);
    }

    static EnderecoRequest getEnderecoRequest() {
        return new EnderecoRequest(LOGRADOURO, null, null, BAIRRO, CIDADE, UF, CEP);
    }
}
