package com.supermarket.mercado.dto.fornecedor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosFornecedorAutenticacao(
        Long idProduto,

        @NotNull
        Long idFornecedor,

        @NotNull
        @Future
        LocalDateTime data) {
}
