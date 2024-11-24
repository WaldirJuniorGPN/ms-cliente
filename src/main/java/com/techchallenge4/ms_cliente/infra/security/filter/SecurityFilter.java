package com.techchallenge4.ms_cliente.infra.security.filter;

import com.techchallenge4.ms_cliente.infra.repository.ClienteRepository;
import com.techchallenge4.ms_cliente.infra.security.utils.token.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.techchallenge4.ms_cliente.domain.exception.Constantes.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenUtils tokenUtils;
    private final ClienteRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            autenticarUsuario(tokenJWT);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarUsuario(String tokenJWT) {
        var subject = tokenUtils.getSubject(tokenJWT);
        var usuario = repository.findByEmailAndAtivoTrue(subject)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));

        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
