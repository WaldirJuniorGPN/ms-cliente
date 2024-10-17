package com.techchallenge4.ms_cliente.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;
}
