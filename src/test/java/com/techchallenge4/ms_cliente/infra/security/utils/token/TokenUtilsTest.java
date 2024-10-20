package com.techchallenge4.ms_cliente.infra.security.utils.token;

import com.auth0.jwt.JWT;
import com.techchallenge4.ms_cliente.domain.model.cliente.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.techchallenge4.ms_cliente.mock.ClienteDados.getCliente;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TokenUtilsTest {

    private Cliente cliente;

    @Autowired
    private TokenUtils tokenUtils;

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration-hours}")
    private Long expirationHours;

    @BeforeEach
    void setUp() {
        cliente = getCliente();
    }

    @Test
    void gerarToken_deveGerarTokenJWT_quandoSucesso() {
        var token = tokenUtils.gerarToken(cliente);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void getSubject_deveRetornarEmail_quandoTokenValido() {
        var token = tokenUtils.gerarToken(cliente);

        var subject = tokenUtils.getSubject(token);

        assertNotNull(subject);
        assertEquals(cliente.getEmail(), subject);
    }

    @Test
    void getSubject_deveLancarExcecao_quandoTokenInvalido() {
        var invalidToken = "invalidToken";

        var exception = assertThrows(RuntimeException.class, () -> tokenUtils.getSubject(invalidToken));
        assertEquals("Token JWT inválido ou expirado", exception.getMessage());
    }

    @Test
    void gerarToken_deveGerarTokenComExpiracaoCorreta() {
        var token = tokenUtils.gerarToken(cliente);

        assertNotNull(token);
        var decodedJWT = JWT.decode(token);
        var expiration = decodedJWT.getExpiresAt().toInstant();

        var expectedExpiration = LocalDateTime.now()
                .plusHours(expirationHours)
                .toInstant(ZoneOffset.of("-03:00"));

        long differenceInSeconds = Math.abs(expiration.getEpochSecond() - expectedExpiration.getEpochSecond());
        assertTrue(differenceInSeconds < 5, "A expiração do token não está correta");
    }
}