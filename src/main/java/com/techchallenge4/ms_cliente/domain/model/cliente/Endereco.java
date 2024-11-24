package com.techchallenge4.ms_cliente.domain.model.cliente;

import com.techchallenge4.ms_cliente.domain.model.enums.UfEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.EnumType.STRING;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Endereco {

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero")
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "uf", length = 2, nullable = false)
    @Enumerated(STRING)
    private UfEnum uf;

    @Column(name = "cep", length = 10, nullable = false)
    private String cep;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;
}
