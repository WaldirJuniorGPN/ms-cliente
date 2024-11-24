package com.techchallenge4.ms_cliente.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoRequest(

        @NotBlank
        String email,

        @NotBlank
        String senha) {
}
