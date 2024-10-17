package com.techchallenge4.ms_cliente.model.dto.request;

import com.techchallenge4.ms_cliente.controller.dto.request.ClienteRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.techchallenge4.ms_cliente.mock.ClienteDados.getClienteRequest;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClienteRequestTest {

    private Validator validator;
    private ValidatorFactory factory;
    private ClienteRequest request;

    @BeforeEach
    void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        request = getClienteRequest();
    }

    @AfterEach
    void tearDown() {
        factory.close();
    }

    @ParameterizedTest
    @MethodSource("nomesInvalidos")
    void deveLancarViolacaoQuandoNomeEstiverInvalido(String nome) {
        request.setNome(nome);
        var violations = validator.validate(request);

        assertTrue(violations.stream().anyMatch( v -> v.getMessage().equals("O nome não pode estar em branco ou nulo")));
    }

    @Test
    void deveLancarViolacaoQuandoEmailForInvalido() {
        request.setEmail("emailInvalido");
        var violations = validator.validate(request);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("O e-mail precisa ter um formato válido: exemplo@exemplo")));
    }

    @Test
    void deveLancarViolacaoQuandoTelefoneForInvalido() {
        request.setTelefone("telefoneInvalido");
        var violations = validator.validate(request);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("O telefone precisa seguir um formato válido: ex. +55 (61) 99999-9999")));
    }

    @Test
    void deveValidarQuandoTodosOsCamposForemValidos() {
        var violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    static Stream<String> nomesInvalidos() {
        return Stream.of(""," ", "     ", null);
    }
}