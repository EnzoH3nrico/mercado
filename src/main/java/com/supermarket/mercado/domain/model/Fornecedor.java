package com.supermarket.mercado.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor {

    private String nome;
    private String cnpj;
    private String telefone;

    public Fornecedor(Fornecedor fornecedor) {
        this.nome = fornecedor.nome;
        this.cnpj = fornecedor.cnpj;
        this.telefone = fornecedor.telefone;
    }

}
