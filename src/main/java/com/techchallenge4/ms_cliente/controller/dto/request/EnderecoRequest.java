package com.techchallenge4.ms_cliente.controller.dto.request;

import com.techchallenge4.ms_cliente.domain.model.enums.UfEnum;
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
    @Schema(description = "Logradouro do endereço", example = "Rua dos Ganchos")
    private String logradouro;

    @Pattern(regexp = "^[0-9A-Za-z]+$", message = "O número contém caracteres inválidos")
    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @Schema(description = "Complemento do endereço", example = "Porão Fétido")
    private String complemento;

    @NotBlank(message = "O bairro não pode estar em branco ou nulo")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\-,. ]+$", message = "O bairro contém caracteres inválidos")
    @Schema(description = "Bairro do endereço", example = "Beco dos Carniceiros")
    private String bairro;

    @NotBlank(message = "A cidade não pode estar em branco ou nulo")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]+$", message = "A cidade contém caracteres inválidos")
    @Schema(description = "Cidade do endereço", example = "Rotten Town")
    private String cidade;

    @Schema(description = "Unidade Federativa (UF) do endereço", example = "PE")
    private UfEnum uf;

    @NotBlank(message = "O CEP não pode estar em branco ou nulo")
    @Pattern(regexp = "^[0-9]{5}-?[0-9]{3}$", message = "O CEP precisa estar no formato 99999-999 ou 99999999")
    @Schema(description = "CEP do endereço", example = "70000-000")
    private String cep;

    @NotBlank(message = "A latitude não pode estar em branco ou nulo")
    @Pattern(regexp = "^-?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)$", message = "A latitude precisa estar no formato -90.0 até 90.0")
    @Schema(description = "Latitude do endereço", example = "-23.5505199")
    private String latitude;

    @NotBlank(message = "A longitude não pode estar em branco ou nulo")
    @Pattern(regexp = "^-?((1?[0-7]?\\d(\\.\\d+)?)|180(\\.0+)?)$", message = "A longitude precisa estar no formato -180.0 até 180.0")
    @Schema(description = "Longitude do endereço", example = "-46.6333094")
    private String longitude;
}
