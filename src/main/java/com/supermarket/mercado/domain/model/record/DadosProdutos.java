package com.supermarket.mercado.domain.model.record;

import com.supermarket.mercado.domain.model.Categoria;
import com.supermarket.mercado.domain.model.Fornecedor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record DadosProdutos(
        @NotBlank(message = "Campo vazio, o item é obrigatório")
        String item,

        @NotBlank(message = "Campo vazio, a marca é obrigatória")
        String marca,

        @NotBlank(message = "Campo vazio, a descrição é obrigatória")
        String descricao,

        @NotNull(message = "Campo vazio, o preço é obrigatório")
        Double preco,

        @NotNull(message = "Campo vazio, o estoque é obrigatório")
        Integer estoque,

        @NotNull (message = "Campo vazio, a categoria é obrigatório")
        Categoria categoria,

        @NotNull(message = "Campo vazio, o fornecedor é obrigatório") @Valid
        Fornecedor fornecedor) {
}
