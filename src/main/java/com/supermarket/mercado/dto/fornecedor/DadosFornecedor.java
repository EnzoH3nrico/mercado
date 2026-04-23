package com.supermarket.mercado.dto.fornecedor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosFornecedor(

        @NotBlank(message = "Campo vazio, o nome é obrigatório.")
        String nome,

        @NotBlank(message = "Campo vazio, o cnpj é obrigatório")
        String cnpj,

        @NotBlank(message = "Campo vazio, o telefone é obrigatório")
        @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
                message = "Este formato de telefone é inválido.")
        String telefone) {
}
