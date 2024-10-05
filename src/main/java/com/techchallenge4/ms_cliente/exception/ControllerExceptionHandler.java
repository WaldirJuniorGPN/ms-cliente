package com.techchallenge4.ms_cliente.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

import static com.techchallenge4.ms_cliente.exception.Constantes.ENTITY_NOT_FOUND;
import static com.techchallenge4.ms_cliente.exception.Constantes.ERRO_VALIDACAO;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final StandardError error = new StandardError();

    @ExceptionHandler(ExceptionAdvice.class)
    public ResponseEntity<StandardError> entityNotFound(ExceptionAdvice exception, HttpServletRequest request) {
        return this.atribuirError(exception, HttpStatus.NOT_FOUND, ENTITY_NOT_FOUND, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException exception, HttpServletRequest request) {
        var validateError = new ValidateError();
        var status = HttpStatus.BAD_REQUEST;
        validateError.setTimestamp(Instant.now());
        validateError.setStatus(status.value());
        validateError.setError(ERRO_VALIDACAO);
        validateError.setMessage(exception.getMessage());
        validateError.setPath(request.getRequestURI());

        for (FieldError f : exception.getBindingResult().getFieldErrors()) {
            validateError.addMensagens(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(validateError);
    }

    private ResponseEntity<StandardError> atribuirError(Exception e, HttpStatus status, String msgError, HttpServletRequest request) {
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError(msgError);
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.error);
    }
}
