package com.supermarket.mercado.model.record;

import com.supermarket.mercado.model.Categoria;
import com.supermarket.mercado.model.Fornecedor;
import jakarta.validation.Constraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.NumberFormat;

public record DadosProdutos(
        @NotBlank
        String item,

        @NotBlank
        String marca,

        @NotBlank
        String descricao,

        @NotNull
        Double preco,

        @NotNull
        Integer estoque,

        @NotNull
        Categoria categoria,

        @NotNull @Valid
        Fornecedor fornecedor) {
}
