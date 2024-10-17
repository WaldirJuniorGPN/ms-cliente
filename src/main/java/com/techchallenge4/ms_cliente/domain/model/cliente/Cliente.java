package com.techchallenge4.ms_cliente.domain.model.cliente;

import com.techchallenge4.ms_cliente.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "Cliente")
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Cliente extends EntidadeBase {

    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Embedded
    private Endereco endereco;
}
