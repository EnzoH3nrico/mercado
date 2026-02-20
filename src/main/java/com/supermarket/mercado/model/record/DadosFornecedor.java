package com.supermarket.mercado.model.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosFornecedor(

        @NotBlank
        String nome,

        @NotBlank
        String cnpj,

        @NotBlank
        @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$\n")
        String telefone) {
}
