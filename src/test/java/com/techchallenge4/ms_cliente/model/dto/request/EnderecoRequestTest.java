package com.techchallenge4.ms_cliente.model.dto.request;

import com.techchallenge4.ms_cliente.controller.dto.request.EnderecoRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.techchallenge4.ms_cliente.mock.ClienteDados.getEnderecoRequest;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnderecoRequestTest {

    private Validator validator;
    private ValidatorFactory factory;
    private EnderecoRequest request;

    @BeforeEach
    void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        request = getEnderecoRequest();
    }

    @AfterEach
    void tearDown() {
        factory.close();
    }

    @ParameterizedTest
    @MethodSource("dadosEmBrancoOuNulo")
    void deveLancarViolacaoQuandoLogradouroEstiverInvalido(String dado) {
        request.setLogradouro(dado);
        var violations = validator.validate(request);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("O logradouro não pode estar em branco ou nulo")));
    }

    @Test
    void deveLancarViolacaoQuandoLogradouroForInvalido() {
        request.setLogradouro("Invalid@@@");
        var violations = validator.validate(request);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("O logradouro contém caracteres inválidos")));
    }

    @ParameterizedTest
    @MethodSource("dadosEmBrancoOuNulo")
    void deveLancarViolacaoQuandoBairroEstiverEmBranco(String dado) {
        request.setBairro(dado);
        var violations = validator.validate(request);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("O bairro não pode estar em branco ou nulo")));
    }

    @Test
    void deveLancarViolacaoQuandoCidadeForInvalida() {
        request.setCidade("123Cidade");
        var violations = validator.validate(request);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("A cidade contém caracteres inválidos")));
    }

    @Test
    void deveLancarViolacaoQuandoUFForInvalida() {
        request.setUf("zz");
        var violations = validator.validate(request);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("A UF deve ser composta por duas letras maiúsculas")));
    }

    @Test
    void deveLancarViolacaoQuandoCEPForInvalido() {
        request.setCep("123456");
        var violations = validator.validate(request);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("O CEP precisa estar no formato 99999-999 ou 99999999")));
    }

    @Test
    void deveValidarQuandoTodosOsCamposForemValidos() {
        var violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }
    static Stream<String> dadosEmBrancoOuNulo() {
        return Stream.of("", " ", "     ", null);
    }
}