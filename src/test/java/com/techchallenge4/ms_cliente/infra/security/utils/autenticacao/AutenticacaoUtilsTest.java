package com.techchallenge4.ms_cliente.infra.security.utils.autenticacao;

import com.techchallenge4.ms_cliente.domain.model.cliente.Cliente;
import com.techchallenge4.ms_cliente.infra.repository.ClienteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static com.techchallenge4.ms_cliente.mock.ClienteDados.getCliente;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AutenticacaoUtilsTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private AutenticacaoUtils autenticacaoUtils;

    private Cliente cliente;
    private String email;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        cliente = getCliente();
        email = cliente.getEmail();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void loadUserByUsername_deveRetornarUserDetails_quandoEmailExiste() {
        when(clienteRepository.findByEmailAndAtivoTrue(email)).thenReturn(Optional.of(cliente));

        var userDetails = autenticacaoUtils.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals(cliente.getEmail(), userDetails.getUsername());
    }

    @Test
    void loadUserByUsername_deveLancarUsernameNotFoundException_quandoEmailNaoExiste() {
        when(clienteRepository.findByEmailAndAtivoTrue(email)).thenReturn(Optional.empty());

        var exception = assertThrows(UsernameNotFoundException.class, () -> autenticacaoUtils.loadUserByUsername(email));
        assertEquals("Usuŕio não encontrado ou inativo", exception.getMessage());
    }
}