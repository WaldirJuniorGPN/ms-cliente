package com.techchallenge4.ms_cliente.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidateMessage {

    private String campo;
    private String mensagem;
}
