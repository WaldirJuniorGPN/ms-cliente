package com.techchallenge4.ms_cliente.infra.security.utils.autenticacao;

import com.techchallenge4.ms_cliente.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.techchallenge4.ms_cliente.domain.exception.Constantes.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class AutenticacaoUtils implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clienteRepository.findByEmailAndAtivoTrue(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }
}
