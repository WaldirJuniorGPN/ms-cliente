package com.techchallenge4.ms_cliente.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequest {

    @NotBlank(message = "O logradouro não pode estar em branco ou nulo")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ0-9'\\-,. ]+$", message = "O logradouro contém caracteres inválidos")
    @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
    private String logradouro;

    @Pattern(regexp = "^[0-9A-Za-z]+$", message = "O número contém caracteres inválidos")
    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @Schema(description = "Complemento do endereço", example = "Apartamento 201")
    private String complemento;

    @NotBlank(message = "O bairro não pode estar em branco ou nulo")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\-,. ]+$", message = "O bairro contém caracteres inválidos")
    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    @NotBlank(message = "A cidade não pode estar em branco ou nulo")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]+$", message = "A cidade contém caracteres inválidos")
    @Schema(description = "Cidade do endereço", example = "Brasília")
    private String cidade;

    @NotBlank(message = "A UF não pode estar em branco ou nulo")
    @Pattern(regexp = "^[A-Z]{2}$", message = "A UF deve ser composta por duas letras maiúsculas")
    @Schema(description = "Unidade Federativa (UF) do endereço", example = "DF")
    private String uf;

    @NotBlank(message = "O CEP não pode estar em branco ou nulo")
    @Pattern(regexp = "^[0-9]{5}-?[0-9]{3}$", message = "O CEP precisa estar no formato 99999-999 ou 99999999")
    @Schema(description = "CEP do endereço", example = "70000-000")
    private String cep;
}
