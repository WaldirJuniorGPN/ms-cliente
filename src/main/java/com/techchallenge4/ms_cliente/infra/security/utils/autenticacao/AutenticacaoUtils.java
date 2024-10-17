package com.techchallenge4.ms_cliente.infra.security.utils.autenticacao;

import com.techchallenge4.ms_cliente.infra.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.techchallenge4.ms_cliente.domain.exception.Constantes.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class AutenticacaoUtils implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLoginAndAtivoTrue(login)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }
}
