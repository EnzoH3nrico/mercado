package com.supermarket.mercado.dto.fornecedor;

import com.supermarket.mercado.model.fornecedor.Fornecedor;

public record DadosDetalhamentoFornecedor(long id, String nome, String cnpj, String telefone) {

    public DadosDetalhamentoFornecedor(Fornecedor fornecedor){
        this(fornecedor.getId(), fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getTelefone());
    }
}
