package com.techchallenge4.ms_cliente.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private EnderecoResponse endereco;
}
