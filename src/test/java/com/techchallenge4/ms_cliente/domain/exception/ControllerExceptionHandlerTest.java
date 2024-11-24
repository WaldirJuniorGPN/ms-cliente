package com.techchallenge4.ms_cliente.domain.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler controllerExceptionHandler;
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        controllerExceptionHandler = new ControllerExceptionHandler();
        request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/test");
    }

    @Test
    void deveretornaarNotFondQuandoEntityNotFoundException() {
        var exception = new ExceptionAdvice("Entity not found");
        var response = controllerExceptionHandler.entityNotFound(exception, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Entity not found", requireNonNull(response.getBody()).getMessage());
        assertEquals(404, response.getBody().getStatus());
        assertEquals("/api/test", response.getBody().getPath());
    }

    @Test
    void deveRetornarInternalServerErrorQuandoExceptionGenerica() {
        var exception = new Exception("Generic error");
        var response = controllerExceptionHandler.handleGenericException(exception, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Generic error", requireNonNull(response.getBody()).getMessage());
        assertEquals(500, response.getBody().getStatus());
    }

    @Test
    void deveRetornarConflictQuandoDataIntegrityViolationException() {
        var exception = new DataIntegrityViolationException("Data integrity violation");
        var response = controllerExceptionHandler.handleDataIntegrityViolation(exception, request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Data integrity violation", requireNonNull(response.getBody()).getMessage());
        assertEquals(409, response.getBody().getStatus());
    }

    @Test
    void deveRetornarBadRequestQuandoMethodArgumentNotValidException() throws Exception {
        var method = this.getClass().getDeclaredMethod("ficticioMethod", String.class);
        var methodParameter = new MethodParameter(method, 0);
        var bindException = new BindException(new Object(), "target");
        bindException.addError(new org.springframework.validation.FieldError("target", "field", "Field error message"));
        var exception = new MethodArgumentNotValidException(methodParameter, bindException);

        var response = controllerExceptionHandler.validation(exception, request);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Alguma propriedade da Requisição é inválida", requireNonNull(response.getBody()).getError());
    }

    private void ficticioMethod(String param) {
    }
}