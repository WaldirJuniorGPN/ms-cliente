package com.techchallenge4.ms_cliente.controller.dto.response;

import com.techchallenge4.ms_cliente.domain.model.enums.UfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponse {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private UfEnum uf;
    private String cep;
    private String latitude;
    private String longitude;
}
