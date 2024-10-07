package com.techchallenge4.ms_cliente.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidateError extends StandardError {

    private List<ValidateMessage> mensagens = new ArrayList<>();

    public void addMensagens(String campo, String msg) {
        this.mensagens.add(new ValidateMessage(campo, msg));
    }
}
