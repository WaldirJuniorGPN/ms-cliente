package com.techchallenge4.ms_cliente.domain.exception;

public class ExceptionAdvice extends RuntimeException {
    public ExceptionAdvice(String message) {
        super(message);
    }
}
