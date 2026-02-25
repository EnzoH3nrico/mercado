package com.supermarket.mercado.domain.produtos;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProdutos(
        @NotNull
        Long id,
        String marca,
        String descricao,
        Double preco) {
}
