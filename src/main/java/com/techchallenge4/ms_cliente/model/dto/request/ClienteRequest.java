package com.techchallenge4.ms_cliente.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotBlank(message = "O nome não pode estar em branco ou nulo")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]+$", message = "O nome contém caracteres inválidos")
    @Schema(description = "Nome completo do cliente", example = "João da Silva")
    private String nome;

    @NotBlank(message = "O e-mail não pode estar em branco ou nulo")
    @Email(message = "O e-mail precisa ter um formato válido: exemplo@exemplo")
    @Schema(description = "E-mail do cliente", example = "joao.silva@email.com")
    private String email;

    @NotBlank(message = "O telefone não pode estar em branco ou nulo")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,}$", message = "O telefone precisa seguir um formato válido: ex. +55 (61) 99999-9999")
    @Schema(description = "Telefone do cliente", example = "+55 (61) 99999-9999")
    private String telefone;

    @Valid
    @Schema(description = "Endereço do cliente")
    private EnderecoRequest endereco;
}
