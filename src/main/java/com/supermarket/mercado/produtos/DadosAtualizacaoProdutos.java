package com.supermarket.mercado.produtos;

import com.supermarket.mercado.model.Fornecedor;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProdutos(
        @NotNull
        Long id,
        String marca,
        String descricao,
        Double preco) {
}
