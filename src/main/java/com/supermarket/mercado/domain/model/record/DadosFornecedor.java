package com.supermarket.mercado.domain.model.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosFornecedor(

        @NotBlank(message = "Campo vazio, o nome é obrigatório.")
        String nome,

        @NotBlank(message = "Campo vazio, o cnpj é obrigatório")
        String cnpj,

        @NotBlank(message = "Campo vazio, o telefone é obrigatório")
        @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$\n",
                message = "Este formato de telefone é inválido.")
        String telefone) {
}
