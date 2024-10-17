package com.techchallenge4.ms_cliente.domain.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constantes {

    public static final String ENTITY_NOT_FOUND = "Entidade não encontrada";
    public static final String ERRO_VALIDACAO = "Alguma propriedade da Requisição é inválida";
    public static final String ERRO_INESPERADO = "Erro inesperado";
    public static final String REGRA_DE_NEGOCIO = "Regra de negócio violada";
    public static final String ERRO_INTEGRIDADE = "Erro de integridade de dados";
    public static final String ERRO_TOKEN_JWT = "Erro ao gerar Token JWT";
    public static final String USER_NOT_FOUND = "Usuŕio não encontrado ou inativo";
    public static final String INVALIDO_TOKEN_JWT = "Token JWT inválido ou expirado";
}
